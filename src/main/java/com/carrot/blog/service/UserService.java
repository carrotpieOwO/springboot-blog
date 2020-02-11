package com.carrot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrot.blog.model.ReturnCode;
import com.carrot.blog.model.user.User;
import com.carrot.blog.model.user.dto.ReqJoinDto;
import com.carrot.blog.model.user.dto.ReqLoginDto;
import com.carrot.blog.repository.UserRepository;

@Service
//서비스는 하나의 기능이 아니라 그냥 트랜잭션 처리해주는 곳,,
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private HttpSession session;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	// 트랜잭션하면 에러안나고 다 수행되면 그때 커밋되고,
	// 예외처리 runtimeException 으로 하면 오류났을때 롤백된다.
	// 예외처리 저거로 안하면 오류난거 캐치못함..
	// 하나만 진행하는거여도 기능이 추가될 수 있으니 일단 추가해주기
	// select는 롤백할거 없으니까 트랜잭션 안걸어도됨
	public int 회원가입(ReqJoinDto dto) {
		try {
			//아이디 중복체크 처리
			int result = userRepository.findByUsername(dto.getUsername());
			
			if(result==1) {
				return ReturnCode.아이디중복;
			}else {
				//패스워드 암호화 하기
				String encodePassword = passwordEncoder.encode(dto.getPassword());
				dto.setPassword(encodePassword);
				return userRepository.save(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	} 
	
	public User 로그인(ReqLoginDto dto) {
		return userRepository.findByUsernameAndPassword(dto);
		
	}
	
//	@Transactional
//	public int 프로필(ReqUpdateDto dto) {
//		try {
//			//아이디 중복체크 처리
//			int result = userRepository.update(dto);
//			
//			if(result==1) {
//				return ReturnCode.성공;
//			}else {
//				return ReturnCode.오류;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ReturnCode.오류;
//		}
//		
//	}
	
	public int 수정완료(int id, String password, String profile, User principal) {
		System.out.println(profile);
		String encodePassword = passwordEncoder.encode(password);
		int result = userRepository.update(id, encodePassword, profile);
		
		if(result==1) {
			User user = userRepository.findById(id);
			//session.setAttribute("principal", user); 
			
			principal.setPassword(user.getPassword());
			principal.setProfile(user.getProfile());
			return 1;
			
		}else {
			return -1;
		}
		
	}
	
	
}
