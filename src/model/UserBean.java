package model;

import java.io.Serializable;

public class UserBean implements Serializable {
	private int id;
	private String userName;
	private String pass;

	public UserBean() {}
	public UserBean(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}
	public UserBean(int id, String userName, String pass) {
		this.id = id;
		this.userName = userName;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}
}
