package com.etc.dao;

import java.util.List;

import com.etc.entity.User;

public interface UserDao {

	public List<User> getUserName(String userName);// 查询单个值

	public List<User> getUser(String userName);// 查询所有值

	public List<User> getUserById();// 查询单个对象

	public void addUser(User u);// 增加用戶

	public void updateUser(User u);// 增加用戶
	
	public void deleteUser(User u);// 删除用戶
	
	public boolean loginUser(String userName,String userPwd);//用户登录
	
	public boolean regUser(User u);//用户注册
}
