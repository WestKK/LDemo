package com.etc.service;

import java.util.List;

import com.etc.entity.User;

public interface UserService {

	public boolean loginUser(String userName, String userPwd);
	
	public boolean regUser(User u);//用户注册
	
	public List<User> findUserName(String userName);// 查询所有值
	
	

}
