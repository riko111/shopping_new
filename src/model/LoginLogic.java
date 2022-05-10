package model;

import dao.UserDAO;

public class LoginLogic {
	public UserBean execute(String userName, String pass) {
		System.out.println("--------------------LoginLogic(execute())--------------------");

		UserBean user = new UserBean(userName, pass);

		UserDAO dao = new UserDAO();
		return dao.findUser(user);
	}

}
