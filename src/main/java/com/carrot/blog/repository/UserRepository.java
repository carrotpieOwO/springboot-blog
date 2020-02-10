package com.carrot.blog.repository;

import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqJoinDto;
import com.carrot.blog.model.user.dto.ReqLoginDto;
import com.carrot.blog.model.user.dto.ReqUpdateDto;

public interface UserRepository {

	int save(ReqJoinDto dto);
	int findByUsername(String username);
	User findByUsernameAndPassword(ReqLoginDto dto);
	int update(int id, String password, String profile);
	User findById(int id);
}
