package com.etc.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter{

	public MyInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String remoteAddr = request.getRemoteAddr();
		String localAddr = request.getLocalAddr();
		Date now = new Date();
		
		System.out.println("preHandle");
		
		if(request.getSession().getAttribute("userName") == null) {
			request.getSession().setAttribute("msg", "用户未登录~");
			
			response.sendRedirect(request.getContextPath()+"/loginMsg/login");
			
			return false;
		}
		System.out.println("remoteAddr:"+remoteAddr+",localAddr:"+localAddr+",now:"+now);
		request.getSession().removeAttribute("msg");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub		
		System.out.println("afterCompletion");
	}
}
