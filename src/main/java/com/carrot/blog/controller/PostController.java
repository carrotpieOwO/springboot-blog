package com.carrot.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.post.Post;
import com.carrot.blog.model.post.dto.ReqDeleteDto;
import com.carrot.blog.model.post.dto.ReqUpdateDto;
import com.carrot.blog.model.post.dto.ReqWriteDto;
import com.carrot.blog.model.user.User;
import com.carrot.blog.repository.PostRepository;
import com.carrot.blog.service.PostService;

@Controller
public class PostController {

	@Autowired
	private HttpSession session;

	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;

	@GetMapping({ "", "/", "/post" })
	public String posts(Model model) {
		List<Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);
		return "/post/list";
	}

	@GetMapping("/post/{id}")
	public String post(@PathVariable int id, Model model) {
		Post post = postRepository.findById(id);
		model.addAttribute("post", post);
		return "/post/detail";
	}

	@GetMapping("/post/write")
	public String write() {
		return "/post/write";

	}

	@GetMapping("/post/update/{postId}")
	public String update(@PathVariable int postId, @RequestParam int userId, Model model) {
		User principal = (User) session.getAttribute("principal");

		if (principal.getId() != userId) {
			return "/user/login";
		} else {
			Post post = postRepository.findById(postId);
			model.addAttribute("post", post);
		}

		return "/post/update";
	}

	@PostMapping("post/write")
	public ResponseEntity<?> update(@Valid @RequestBody ReqWriteDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println(errorMap);
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		int result = postService.글쓰기(dto);

		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("post/update")
	public ResponseEntity<?> update(@Valid @RequestBody ReqUpdateDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println(errorMap);
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		int result = postRepository.update(dto);

		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("post/delete") //데이터 받는 방식이 다르면 맵핑 주소 같아도 상관없다. 
	public @ResponseBody ResponseEntity<?> delete(@RequestBody ReqDeleteDto dto) {
		User principal = (User) session.getAttribute("principal");
		if(principal.getId()==dto.getUserId()) {
			int result = postRepository.delete(dto.getId());
			if(result==1) {
				return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
			}else {
				return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			
		
		return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
}
