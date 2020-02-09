package com.carrot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.post.dto.ReqWriteDto;
import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqLoginDto;
import com.carrot.blog.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public int 글쓰기(ReqWriteDto dto) {
		try {
			//아이디 중복체크 처리
			int result = postRepository.write(dto);
			
			if(result==1) {
				return ReturnCode.성공;
			}else {
				return ReturnCode.오류;
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
}
