package com.carrot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carrot.blog.model.user.User;
import com.carrot.blog.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	public User getPrincipal() {
		return user;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = userRepository.authentication(username);
		//패스워드는 알아서 매칭해주고 유저네임 있는지만 찾으면 된다. 패스워드 아예 개발자도 손안됨 그럼 해커도 모름
		if(user==null) {
			throw new UsernameNotFoundException("해당 유저가 없습니다.");
		}else {
			return user;
		}
	}
	

}
