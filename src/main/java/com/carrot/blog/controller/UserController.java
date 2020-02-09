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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqJoinDto;
import com.carrot.blog.model.user.dto.ReqLoginDto;
import com.carrot.blog.model.user.dto.ReqUpdateDto;
import com.carrot.blog.repository.UserRepository;
import com.carrot.blog.service.UserService;

@Controller
public class UserController {

	private static final String TAG = "UserController:";

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserRepository userRepository;

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
	}

	@GetMapping("user/profile/{id}")
	public String profile(@PathVariable int id) {
		User principal = (User)session.getAttribute("principal");
		
		
			if(principal.getId() == id) {
				return "/user/profile";
			}else {
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
		
		User principal = userService.로그인(dto);
		if(principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("user/profile")
	public ResponseEntity<?> profile(@Valid @RequestBody ReqUpdateDto dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println(errorMap);
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		
		int result = userService.프로필(dto);
		int id = dto.getId();
		if(result == 1) {
			User user = userRepository.findById(id);
			session.setAttribute("principal", user);
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
		
	}
}
