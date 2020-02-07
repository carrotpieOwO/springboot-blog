package com.carrot.blog.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.Handler;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//어댑터 패턴 
//@Controller, @Configuration, @Service, @Repository
//@Component -- 뭔지모르겟지만 싱글톤으로 뭘 띄우고싶을때 이거 쓰는거 / 핸들러도 재사용할거면 이거 걸기
public class SessionInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("principal")==null) {
			System.out.println("인증안됨 튕겨");
			response.sendRedirect("/user/login");
			return false;
		}
		System.out.println("인증됨 들어와");
		return true;
	}
	//리턴이 트루면 쭉 들어가고 펄스면 막는다. 
}
