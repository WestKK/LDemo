package com.etc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import com.etc.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao ud;

	@Override
	public boolean loginUser(String userName, String userPwd) {
		// TODO Auto-generated method stub
		return ud.loginUser(userName, userPwd);
	}

	@Override
	public boolean regUser(User u) {
		// TODO Auto-generated method stub
		return ud.regUser(u);
	}

	@Override
	public List<User> findUserName(String userName) {
		// TODO Auto-generated method stub
		return ud.getUser(userName);
	}


}
