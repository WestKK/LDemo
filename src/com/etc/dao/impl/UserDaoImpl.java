package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.etc.dao.UserDao;
import com.etc.entity.User;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

	// 要用jdbcTemplate
	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> getUser(String userName) {
		// TODO Auto-generated method stub
		List<User> list = jdbcTemplate.query("SELECT `user`.userId,`user`.userName,`user`.userPwd,`user`.userSex FROM `user` WHERE `user`.userName NOT IN (?)", new Object[] {userName},new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setUserName(rs.getString("userName"));
				return u;
			}
		});

		return list;
	}

	@Override
	public List<User> getUserName(String userName) {
		// TODO Auto-generated method stub
		List<User> users = jdbcTemplate.query("select userName  from user where userId = 1 ", new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setUserName(rs.getString("userName"));
				return u;
			}
		});

		return users;
	}

	@Override
	public List<User> getUserById() {
		// TODO Auto-generated method stub
		List<User> users = jdbcTemplate.query("select userId,userName,userSex from user where userId = 1 ",
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u = new User();
						u.setUserId(rs.getInt("userId"));
						u.setUserName(rs.getString("userName"));
						u.setUserSex(rs.getString("userSex"));
						return u;
					}
				});

		return users;
	}

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("INSERT INTO user VALUES('" + u.getUserId() + "', '" + u.getUserName() + "', '"
				+ u.getUserSex() + "')");

	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("UPDATE user SET userSex = ? WHERE userName = ?",
				new Object[] { u.getUserSex(), u.getUserName() });
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from user where userId = ?", new Object[] { u.getUserId() });
	}

	@Override
	public boolean loginUser(String userName, String userPwd) {
		// TODO Auto-generated method stub
		RowMapper<User> rowMapper = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				User u = new User();
				u.setUserId(rs.getInt("userId"));
				return u;
			}
		};

		List<User> list = jdbcTemplate.query("select * from user where userPwd= ? and userName = ? ",
				new Object[] { userPwd, userName }, rowMapper);

		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean regUser(User u) {
		// TODO Auto-generated method stub
		String sql = "insert into user(userName,userPwd) values(?,?)";
		int n = jdbcTemplate.update(sql, u.getUserName(), u.getUserPwd());
		if (n > 0) {
			return true;
		}
		return false;
	}
}
