package com.carrot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrot.blog.model.RespCM;
import com.carrot.blog.model.comment.dto.ReqDetailDto;
import com.carrot.blog.model.comment.dto.RespDetailDto;
import com.carrot.blog.service.CommentService;

//@Controller + @ResponseBody : 모든게 다 데이터고, 뷰리졸버가 관여안함
@RestController 
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/write")
	public ResponseEntity<?> write(@RequestBody ReqDetailDto dto) {
		RespDetailDto comment = commentService.댓글쓰기(dto);
		
		//ok, fail, comment 리턴
		if(comment != null) {
			comment.setStatus(new RespCM(200,"ok"));
			//comment 안에 RespCM 이 내장되 있어서 comment만 적어서 데이터랑 같이 보내기
			return new ResponseEntity<RespDetailDto>(comment, HttpStatus.OK);
		}else {
			RespDetailDto error = new RespDetailDto();
			error.setStatus(new RespCM(400,"fail"));
			return new ResponseEntity<RespDetailDto>(error, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/comment/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		int result = commentService.댓글삭제(id);
		
		//ok, fail, comment 리턴
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else if(result==-3) {
			return new ResponseEntity<RespCM>(new RespCM(403, "fail"), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
