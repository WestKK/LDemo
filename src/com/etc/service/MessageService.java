package com.etc.service;

import com.etc.entity.Message;
import com.etc.util.PageData;

public interface MessageService {

	public PageData<Message> getMessageByPage(int page, int pageSize, String keywords);
	
	public boolean delMessage(int messageId);
	
	public boolean addMessage(Message m);
	
	public boolean changeState(int messageId,int state);

	public boolean updateMessage(Message m);
}
