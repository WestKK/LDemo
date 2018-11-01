package com.etc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etc.dao.UserDao;
import com.etc.entity.User;

public class TestJdbcTemplate2 {

	// 查询单个对象
	@Test
	public void queryUserNameById() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDao userDao = context.getBean(UserDao.class);

		userDao.getUserById().forEach(System.out::println);

	}

	// 增加
	@Test
	public void testAddUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDao userDao = context.getBean(UserDao.class);

		// User u = new User(100, "小王", "男");

		// userDao.addUser(u);

	}

	// 更新
	@Test
	public void testUpdateUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDao userDao = context.getBean(UserDao.class);

		/*
		 * User u = new User(100, "小王", "女");
		 * 
		 * userDao.updateUser(u);
		 */

	}

	// 删除
	@Test
	public void testDeleteUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDao userDao = context.getBean(UserDao.class);

		// User u = new User(100, "小王", "女");

		// userDao.deleteUser(u);

	}
	
	@Test
	public void testLogin() {
		

	}

}
