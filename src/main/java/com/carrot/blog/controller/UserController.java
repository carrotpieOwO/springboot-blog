package com.carrot.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqJoinDto;
import com.carrot.blog.model.user.dto.ReqLoginDto;
import com.carrot.blog.service.UserService;

@Controller
public class UserController {

	private static final String TAG = "UserController:";

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session; 

	@GetMapping("user/join")
	public String join() {
		return "/user/join";
	}

	@GetMapping("user/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
		//이렇게 하면 location.href 데이터 들고가는거 아니라 그냥 이동하는거의 메소드를 때리는 것. 어차피 / 여기로 가면서 데이터처리할거니까
	}
	
	//인증, 동일인 체크
	@GetMapping("user/profile/{id}")
	public String profile(@PathVariable int id) {
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal != null) {
			if(principal.getId() == id) {
				return "/user/profile";
			}else {
				//잘못된 접근입니다 이런거 띄워보기
				//리스폰스 바디 자체거 버퍼드 라이트라서 out. 이런식으로 안해도된다.
				return "/user/login";
			}
		}else {
			//인증되지 않은 사용자입니다 로그인해주세요
			return "/user/login";
		}
	}

	// 메시지 컨버터는 request받을 때 setter로 호출함
	// ResponseEntity 붙이면 ResponseBody 안붙여도됨
	@PostMapping("user/join")
	public ResponseEntity<?> join(@Valid @RequestBody ReqJoinDto dto, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println(errorMap);
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		
		int result = userService.회원가입(dto);
		
		
		if(result == -2) {
			return new ResponseEntity<RespCM>(new RespCM(ReturnCode.아이디중복,"아이디중복"), HttpStatus.OK);
		}else if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500,"fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("user/login")
	public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDto dto, BindingResult bindingResult) {
		// 1.서비스 호출
		// principal = 인증 주체
		User principal = userService.로그인(dto);
		if(principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseEntity<RespCM>(new RespCM(200,"ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(200,"fail"), HttpStatus.BAD_REQUEST);
		}
		
	}
}
