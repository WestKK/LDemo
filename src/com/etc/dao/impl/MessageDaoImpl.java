package com.etc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.etc.dao.MessageDao;
import com.etc.entity.Message;

@Repository(value = "messageDao")
public class MessageDaoImpl implements MessageDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Message> getMessageByPage(int start, int pageSize, String keywords) {
		// TODO Auto-generated method stub
		String sql = "SELECT message.messageId, message.senderName, message.receiveName, message.content, message.time, message.state FROM message WHERE content LIKE ? LIMIT ?, ?";

		RowMapper<Message> rowMapper = new RowMapper<Message>() {

			@Override
			public Message mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.setMessageId(rs.getInt("messageId"));
				message.setSenderName(rs.getString("senderName"));
				message.setReceiveName(rs.getString("receiveName"));
				message.setContent(rs.getString("content"));
				message.setTime(rs.getString("time"));
				message.setState(rs.getInt("state"));
				return message;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { "%" + keywords + "%", start, pageSize }, rowMapper);
	}

	@Override
	public int getTotalMessage(String keywords) {
		// TODO Auto-generated method stub

		String sql = "SELECT count(message.messageId) FROM message WHERE content LIKE ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { "%" + keywords + "%" }, Integer.class);

	}

	@Override
	public boolean delMessage(int messageId) {
		// TODO Auto-generated method stub
		String sql = "delete from message where messageId = ?";
		return jdbcTemplate.update(sql, new Object[] { messageId }) > 0;
	}

	@Override
	public boolean addMessage(Message m) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "INSERT INTO message( senderName,receiveName, content, time, state) VALUES (?, ?,?, ?, 0)";
		return jdbcTemplate.update(sql,
				new Object[] { m.getSenderName(), m.getReceiveName(), m.getContent(), df.format(new Date()) }) > 0;
	}

	@Override
	public boolean changeState(int messageId, int state) {
		// TODO Auto-generated method stub
		String sql = "update message set state = ? where messageId = ?";
		return jdbcTemplate.update(sql, new Object[] { state, messageId }) > 0;
	}

	@Override
	public boolean updateMessage(Message m) {
		// TODO Auto-generated method stub
		String sql = "update message set content = ? ,time = ? where messageId = ?";
		return jdbcTemplate.update(sql, new Object[] { m.getContent(), m.getTime(), m.getMessageId() }) > 0;
	}

}
