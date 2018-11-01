package com.etc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etc.entity.User;
import com.etc.service.UserService;

@Controller
public class LoginController {

	@Resource(name = "userService")
	private UserService us;

	@RequestMapping(value = "login")
	public String login(String userName, String userPwd,HttpSession session) {

		boolean flag = us.loginUser(userName, userPwd);
		
		session.setAttribute("userName", userName);

		if (flag) {
			return "messageList";
		}
		return "login";
	}

	@RequestMapping(value = "regUser")
	public String reg(User u, Model model) {

		boolean flag = us.regUser(u);

		Map<String, User> map = new HashMap<String, User>();
		map.put("u", u);
		model.addAttribute("map", map);
		System.out.println(flag);
		if (flag) {
			return "login";
		}
		return null;
	}

/*	@RequestMapping(value = "/aaa")
	public String login(Model model) {
		model.addAttribute("key", "value");
		List<String> list = new ArrayList<String>();
		list.add("老王不再住隔壁");
		list.add("老宋不做经纪人");
		model.addAttribute("list", list);
		Map<String, String> map = new HashMap<String, String>();
		map.put("xiaoyi", "厦门");
		map.put("xiaoer", "福州");
		map.put("xiaosan", "上海");
		model.addAttribute("map", map);
		return "index";

	}*/

}
