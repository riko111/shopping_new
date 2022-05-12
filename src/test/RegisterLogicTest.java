package test;

import dao.UserDAO;
import model.UserBean;

public class RegisterLogicTest {
	public static void main(String[] args) {
		// ■テスト１
//		String userName = "user0003"; //この値を変更してテストする
//		UserBean loginUser = testExecute(userName, "user99pass");
//		System.out.println("loginUserインスタンス(" + loginUser.getId() + ", " + loginUser.getUserName() + ", " + loginUser.getPass() + ")");
		// ■テスト２
		String[] array = {
			"user0008",
			"USER0008",
			"useR0008",
			"user013pass123456789"
		};

		for(String str : array) {
			System.out.println("「" + str + "」 " + isValidInput(str));
//			System.out.println(isValidInput(str) + "\r\n");
		}
	}

	public static UserBean testExecute(String userName, String pass) {
		System.out.println("--------------------RegisterLogicTest(testExecute(userName, pass))--------------------");

		UserBean user = new UserBean(userName, pass);

		UserDAO dao = new UserDAO();
		dao.registerUser(user);

		// 登録後、DBから取得
		return dao.findUser(user);
	}

	public static boolean isValidInput(String str) {

		return str.matches("^(?=.*[a-z|A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$"); //正規表現（肯定の先読み）
	}
}
