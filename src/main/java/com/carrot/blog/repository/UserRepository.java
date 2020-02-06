package com.carrot.blog.repository;

import com.carrot.blog.model.user.dto.ReqJoinDto;

public interface UserRepository {

	int save(ReqJoinDto dto);
	int findByUsername(String username);
}
