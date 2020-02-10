package com.carrot.blog.repository;

import com.carrot.blog.model.comment.dto.ReqDetailDto;
import com.carrot.blog.model.comment.dto.RespDetailDto;

public interface CommentRepository {
	public int save(ReqDetailDto dto);
	public RespDetailDto findById(int id);
	public int delete(int id);
}
