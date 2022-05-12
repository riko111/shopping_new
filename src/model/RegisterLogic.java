package model;

import dao.UserDAO;

public class RegisterLogic {
	public UserBean execute(String userName, String pass) {
		System.out.println("--------------------RegisterLogic(execute(userName, pass))--------------------");


		UserBean user = new UserBean(userName, pass);
		UserBean loginUser = null;

		UserDAO dao = new UserDAO();

		// アカウント登録処理
		if(dao.registerUser(user)) {
			// 登録したアカウント情報をDBから取得
			loginUser = dao.findUser(user);
		}
	return loginUser;
	}

	public boolean isValidInput(String str) {
//		return str.matches("^(?=.*[a-z])(?=.*[0-9]){8,15}$"); //正規表現（肯定の先読み）
		return str.matches("^[a-z0-9]{8,15}$"); //正規表現（肯定の先読み）
	}
}
