package com.etc.entity;

public class User {
	private int userId;
	private String userName;
	private String userSex;
	private String userPwd;

	public int getUserId() {
		return userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String userSex, String userPwd) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userSex = userSex;
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", userPwd=" + userPwd
				+ "]";
	}

}
