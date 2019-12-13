package com.model.user;

public class User {
	private String userName,userPassword,userRealName;

	public User() {}
	
	public User(String userName, String userPassword, String userRealName) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRealName = userRealName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
}
