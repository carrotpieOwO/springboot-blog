package com.carrot.blog.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter{
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	
	HttpSession session = request.getSession();
	if(session.getAttribute("principal")==null) {
		response.sendRedirect("/user/login");
		return false;
	}else {
		return true;
	}
}
}
