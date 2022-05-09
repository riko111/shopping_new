package model;

import dao.UserDAO;

public class RegisterLogic {
	public UserBean execute(String userName, String pass) {
		System.out.println("--------------------RegisterLogic(execute(userName, pass))--------------------");

		UserBean user = new UserBean(userName, pass);
		UserBean loginUser = null;

		UserDAO dao = new UserDAO();
		if(dao.registerUser(user)) {
			// 登録後、DBから取得
			loginUser = dao.findUser(user);
		}
		return loginUser;

	}
}
