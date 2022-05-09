package test;

import dao.UserDAO;
import model.UserBean;

public class RegisterLogicTest {
	public static void main(String[] args) {
		String userName = "user0003"; //この値を変更してテストする

		UserBean loginUser = testExecute(userName, "user99pass");

		System.out.println("loginUserインスタンス(" + loginUser.getId() + ", " + loginUser.getUserName() + ", " + loginUser.getPass() + ")");
	}

	public static UserBean testExecute(String userName, String pass) {
		System.out.println("--------------------RegisterLogicTest(testExecute(userName, pass))--------------------");

		UserBean user = new UserBean(userName, pass);

		UserDAO dao = new UserDAO();
		dao.registerUser(user);

		// 登録後、DBから取得
		return dao.findUser(user);

	}
}
