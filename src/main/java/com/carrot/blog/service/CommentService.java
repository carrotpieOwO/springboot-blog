package com.carrot.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.comment.dto.ReqDetailDto;
import com.carrot.blog.model.comment.dto.RespDetailDto;
import com.carrot.blog.model.user.User;
import com.carrot.blog.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private HttpSession session;
	
	public RespDetailDto 댓글쓰기(ReqDetailDto dto) {
		int result = commentRepository.save(dto);
		
		if(result == 1) {
			//select 
			return commentRepository.findById(dto.getId());
		}else {
			return null;
		}
		
		
	}
	
	public List<RespDetailDto> 댓글목록(int id) {
		List<RespDetailDto> respDetailDto = commentRepository.findByPostId(id);
		return respDetailDto;
	}
	
	public int 댓글삭제(int id) {
		
		//누가썼는지 확인
		RespDetailDto comment = commentRepository.findById(id);
		
		//로그인 주체 확인
		//User principal = (User) session.getAttribute("principal");
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(comment.getUserId()==principal.getId()) {
			return commentRepository.delete(id);
		}else {
			return ReturnCode.권한없음;
		}
		
	}
}
