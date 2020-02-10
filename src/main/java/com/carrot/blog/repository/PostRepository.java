package com.carrot.blog.repository;

import java.util.List;

import com.carrot.blog.model.post.Post;
import com.carrot.blog.model.post.dto.ReqUpdateDto;
import com.carrot.blog.model.post.dto.ReqWriteDto;
import com.carrot.blog.model.post.dto.RespListDto;

public interface PostRepository {
	public int write(ReqWriteDto dto);
	public List<RespListDto> findAll();
	public Post findById(int id);
	public int update(ReqUpdateDto dto);
	public int delete(int id);
}
