package com.carrot.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carrot.blog.model.user.User;

@Controller
public class PostController {

	@Autowired
	private HttpSession session;

	@GetMapping({ "", "/", "/post" })
	public String posts() {
		return "/post/list";
	}

	@GetMapping("/post/{id}")
	public String post() {
		return "/post/detail";
	}

	// 인증체크필요
	@GetMapping("/post/write")
	public String write() {

		if (session.getAttribute("principal") != null) {
			return "/post/write";
		} else {
			return "redirect:/user/login";
		}

	}

	// 인증체크필요, 동일인 체크
	@GetMapping("/post/update/{id}")
	public String update(@PathVariable int id) {

		User principal = (User) session.getAttribute("principal");

		if (principal.getId() == id) {
			return "/post/update";
		} else {
			return "/user/login";
		}

	}
}