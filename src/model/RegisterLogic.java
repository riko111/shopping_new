package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;

public class RegisterLogic {

	public UserBean execute(String userName, String pass) {
		System.out.println("--------------------RegisterLogic(execute(userName, pass))--------------------");

		// パスワードをハッシュ化
		String hashedPass = null;
		try {
			hashedPass = build(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		UserBean user = new UserBean(userName, hashedPass);
		UserBean loginUser = null;
		UserDAO dao = new UserDAO();

		// アカウント登録処理
		if(dao.registerUser(user)) {
			// 登録したアカウント情報をDBから取得
			loginUser = dao.findUser(user);
		}
		return loginUser;
	}

	// ■ハッシュ化 //LoginLogicでも使用するメソッドなので別クラスに作るべきか
	public static String build (String pass) throws NoSuchAlgorithmException {
		System.out.println("パスワードをハッシュ化する");

		StringBuilder buff = new StringBuilder();
		if (pass != null && !pass.isEmpty()) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(pass.getBytes());
			byte[] digest = md.digest();

			for (byte d : digest) {
				buff.append((int)d&0xFF);
			}
		}
		System.out.println("ハッシュ化したパスワード=" + buff.toString());
		return buff.toString();
		}

	// ■入力チェック ※register.jspで指定
	public static boolean isValidInput(String str) {
		return str.matches("^(?=.*[a-z|A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$"); //正規表現（肯定の先読み）
	}
}
