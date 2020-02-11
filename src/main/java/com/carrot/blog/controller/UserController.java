package com.carrot.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqJoinDto;
import com.carrot.blog.repository.UserRepository;
import com.carrot.blog.service.UserService;

@Controller
public class UserController {

	private static final String TAG = "UserController:";
	
	@Value("${file.path}")
	private String fileRealPath; //서버에 배포하면 경로 변경해야함
	
	@Autowired
	private UserService userService;
//	@Autowired
//	private HttpSession session;
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
	
//	@GetMapping("user/logout")
//	public String logout() {
//		session.invalidate();
//		return "redirect:/";
//	}

	@GetMapping("user/profile/{id}")
	public String profile(@PathVariable int id, 
			@AuthenticationPrincipal User principal) {
		//User principal = (User)session.getAttribute("principal");
		
		
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
	
	/*
	 * @PostMapping("user/login") public ResponseEntity<?> login(@Valid @RequestBody
	 * ReqLoginDto dto, BindingResult bindingResult) {
	 * 
	 * User principal = userService.로그인(dto); if(principal != null) {
	 * session.setAttribute("principal", principal); return new
	 * ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK); }else { return
	 * new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
	 * }
	 * 
	 * }
	 */
	
//	@PutMapping("user/profile")
//	public ResponseEntity<?> profile(@Valid @RequestBody User user, BindingResult bindingResult) {
//		/*if(bindingResult.hasErrors()) {
//			Map<String, String> errorMap = new HashMap<>();
//			
//			for(FieldError error:bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//			}
//			System.out.println(errorMap);
//			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
//		}
//		
//		int result = userService.프로필(dto);
//		int id = dto.getId();
//		if(result == 1) {
//			User user = userRepository.findById(id);
//			session.setAttribute("principal", user);
//			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
//		}else {*/
//			//return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
//		System.out.println("UserController: "+user.getId());
//		System.out.println("UserController: "+user.getPassword());
//		
//		return null;
//		}
//		
//	}
	
	//form:form 사용함
	@PutMapping("user/profile")
	public @ResponseBody String profile(@RequestParam int id, @RequestParam String password, @RequestParam MultipartFile profile, 
			@AuthenticationPrincipal User principal) {
		//사진이 여러장 일땐 MultipartFile[] 이렇게 배열로 받기
		System.out.println(profile);
		
		UUID uuid = UUID.randomUUID();
		String uuidFilename;
		
		if(profile != null) {
			uuidFilename= uuid+"_"+profile.getOriginalFilename();
		}else {
			uuidFilename="";
		}
		
		//nio 객체 ! - 사진, 동영상, 스트리밍 다 지원 해줌
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		try {
			Files.write(filePath, profile.getBytes());
			//옵션은 yml 에서 걸꺼기 때문에 여기서 안건다.
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		int result = userService.수정완료(id, password, uuidFilename, principal);
		StringBuffer sb = new StringBuffer();
		if(result==1) {
			//여기서 그냥 "/" 라고하면 데이터 안들고감..
			//return "redirect:/";
			sb.append("<script>");
			sb.append("alert('수정완료');");
			sb.append("location.href='/';");
			sb.append("</script>");
			return sb.toString();
			
		}else {
			sb.append("<script>");
			sb.append("alert('수정실패');");
			sb.append("history.back();");
			sb.append("</script>");
			return sb.toString();
		}		

	}
}
