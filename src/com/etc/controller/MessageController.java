package com.etc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.etc.entity.Message;
import com.etc.entity.User;
import com.etc.service.MessageService;
import com.etc.service.UserService;
import com.etc.util.PageData;

@Controller
@RequestMapping(value = "/message")
public class MessageController {

	@Resource(name = "messageService")
	private MessageService ms;

	@Resource(name = "userService")
	private UserService us;

	@RequestMapping(value = "/list")
	public ModelAndView listMessage(@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = true, defaultValue = "5") int pageSize,
			@RequestParam(value = "keywords", required = true, defaultValue = "") String keywords) {

		ModelAndView mv = new ModelAndView("messageList");

		PageData<Message> pd = ms.getMessageByPage(page, pageSize, keywords);

		mv.addObject("pd", pd);

		mv.addObject("keywords", keywords);

		return mv;

	}

	@RequestMapping(value = "/del/{messageId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delMessage(@PathVariable("messageId") int messageId) {

		boolean flag = ms.delMessage(messageId);

		return flag+"";

	}

	@RequestMapping(value = "/findName", method = RequestMethod.GET)
	@ResponseBody
	public List<User> delMessage(String senderName) {

		List<User> list = us.findUserName(senderName);

		System.out.println(list);

		return list;

	}

	@RequestMapping(value = "/addMsg", method = RequestMethod.POST)
	@ResponseBody
	public String addMessage(Message m) {

		boolean flag = ms.addMessage(m);

		return flag+"";

	}
	
	@RequestMapping(value = "/change/{messageId}", method = RequestMethod.PUT)
	@ResponseBody
	public String changeMessage(@PathVariable("messageId") int messageId,@RequestBody Map<String,Integer> map) {
		
		System.out.println(map.get("state"));
		boolean flag = ms.changeState(messageId, map.get("state"));

		return flag+"";

	}
	
	@RequestMapping(value = "/update/{messageId}", method = RequestMethod.PUT)
	@ResponseBody
	public boolean updateMessage(@PathVariable("messageId") int messageId,@RequestBody Map<String,String> map) {

		String content = map.get("content");
		
		String time = map.get("time");
		
		Message m = new Message(messageId, content, time);
		
		boolean flag = ms.updateMessage(m);

		return flag;

	}
}
