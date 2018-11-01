package com.etc.dao;

import java.util.List;

import com.etc.entity.Message;

public interface MessageDao {

	public List<Message> getMessageByPage(int start, int pageSize, String keywords);

	public int getTotalMessage(String keywords);

	public boolean delMessage(int messageId);
	
	public boolean addMessage(Message m);
	
	public boolean changeState(int messageId,int state);
	
	public boolean updateMessage(Message m);
	
}
