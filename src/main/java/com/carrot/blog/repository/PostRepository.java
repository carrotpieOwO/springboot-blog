package com.carrot.blog.repository;

import java.util.List;

import com.carrot.blog.model.post.Post;
import com.carrot.blog.model.post.dto.ReqUpdateDto;
import com.carrot.blog.model.post.dto.ReqWriteDto;

public interface PostRepository {
	int write(ReqWriteDto dto);
	List<Post> findAll();
	Post findById(int id);
	int update(ReqUpdateDto dto);
	int delete(int id);
}
