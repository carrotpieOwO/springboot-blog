package com.carrot.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.post.Post;
import com.carrot.blog.model.post.dto.ReqUpdateDto;
import com.carrot.blog.model.post.dto.ReqWriteDto;
import com.carrot.blog.model.post.dto.RespListDto;
import com.carrot.blog.model.user.User;
import com.carrot.blog.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
//	
//	@Autowired
//	private HttpSession session;
	
	@Autowired
	private MyUserDetailService userDetailService;
	
	public int 글쓰기(ReqWriteDto dto) {
		return postRepository.write(dto);
	}
	
	public List<RespListDto> 목록보기(){
		return postRepository.findAll();
	}
	
	public Post 상세보기(int id) {
		return postRepository.findById(id);
	}
	
	public Post 수정하기(int id) {
		//User principal = (User) session.getAttribute("principal");
		Post post = postRepository.findById(id);
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal.getId() == post.getUserId()) {
			return post;
		}else {
			return null;
		}
	}
	
	public int 수정완료(ReqUpdateDto dto) {
		//User principal = (User) session.getAttribute("principal");
		Post post = postRepository.findById(dto.getId());
		
		//직접 세션 객체 접근하여 가져오기
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//아래 방법으로 사용해도 무방하나 위방법으로 하면 세션에서 바로 가져올 수 있다.
		//User principal = userDetailService.getPrincipal();
		if(principal.getId()==post.getUserId()) {
			return postRepository.update(dto);
		}else {
			return ReturnCode.권한없음;
		}
	}
	
	public int 삭제하기(int id) {
		//User principal = (User) session.getAttribute("principal");
		Post post = postRepository.findById(id);
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal.getId()==post.getUserId()) {
			return postRepository.delete(id);
		}else {
			return ReturnCode.권한없음;
		}
	}

}
