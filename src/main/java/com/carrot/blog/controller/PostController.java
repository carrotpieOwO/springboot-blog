package com.carrot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.post.dto.ReqUpdateDto;
import com.carrot.blog.model.post.dto.ReqWriteDto;
import com.carrot.blog.model.user.User;
import com.carrot.blog.service.CommentService;
import com.carrot.blog.service.PostService;

@Controller
public class PostController {

//	@Autowired
//	private HttpSession session;

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
//	@Autowired
//	AuthenticationPrincipal authenticationPrincipal;

	@GetMapping({ "", "/", "/post" })
	public String posts(Model model) {

		model.addAttribute("posts", postService.목록보기());
		return "/post/list";
	}

	@GetMapping("/post/{id}")
	public String post(@PathVariable int id, Model model) {

		return "/post/detail";
	}

	@GetMapping("/post/write")
	public String write() {
		return "/post/write";

	}

	@GetMapping("/post/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.상세보기(id));
		model.addAttribute("comments", commentService.댓글목록(id));

		return "/post/detail";
	}
	
	@GetMapping("/post/update/{postId}")
	public String update(@PathVariable int postId, Model model,
			@AuthenticationPrincipal User principal) {
		model.addAttribute("post", postService.수정하기(postId, principal));
		return "/post/update";
	}

	@PostMapping("/post/write")
	public ResponseEntity<?> update(@RequestBody ReqWriteDto dto, BindingResult bindingResult, @AuthenticationPrincipal User principal
) {
		dto.setUserId(principal.getId());

		int result = postService.글쓰기(dto);

		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/post/update")
	public ResponseEntity<?> update(@RequestBody ReqUpdateDto dto, 
			@AuthenticationPrincipal User principal) {

		int result = postService.수정완료(dto, principal);

		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else if (result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/post/delete/{id}") // 데이터 받는 방식이 다르면 맵핑 주소 같아도 상관없다.
	public @ResponseBody ResponseEntity<?> delete(@PathVariable int id, 
			@AuthenticationPrincipal User principal) {
		int result = postService.삭제하기(id, principal);
		if (result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else if (result == -3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);

		}

	}
}
