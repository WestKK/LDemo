package com.etc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etc.dao.MessageDao;
import com.etc.entity.Message;
import com.etc.service.MessageService;
import com.etc.util.PageData;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

	@Resource(name = "messageDao")
	private MessageDao md;

	@Override
	public PageData<Message> getMessageByPage(int page, int pageSize, String keywords) {
		// TODO Auto-generated method stub

		if (page <= 0) {
			page = 1;
		}
		
		if (pageSize <= 0) {
			pageSize = 5;
		}

		int start = (page - 1) * pageSize;

		List<Message> list = md.getMessageByPage(start, pageSize, keywords);

		int total = md.getTotalMessage(keywords);

		if (pageSize >= total) {
			pageSize = total;
		}

		PageData<Message> pd = new PageData<Message>(list, total, pageSize, page);

		return pd;
	}

	@Override
	public boolean delMessage(int messageId) {
		// TODO Auto-generated method stub
		return md.delMessage(messageId);
	}

	@Override
	public boolean addMessage(Message m) {
		// TODO Auto-generated method stub
		return md.addMessage(m);
	}

	@Override
	public boolean changeState(int messageId, int state) {
		// TODO Auto-generated method stub
		return md.changeState(messageId, state);
	}

	@Override
	public boolean updateMessage(Message m) {
		// TODO Auto-generated method stub
		return md.updateMessage(m);
	}

}
