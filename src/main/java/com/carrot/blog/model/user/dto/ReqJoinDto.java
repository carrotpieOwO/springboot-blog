package com.carrot.blog.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqJoinDto {
	
	@Size(min=7, max=15, message="유저네임은 7~15자까지 입력 가능합니다.")
	@NotBlank(message="유저네임을 입력하세요")
	private String username;
	
	@Size(min=7, max=15, message="패스워드는 7~15자까지 입력 가능합니다.")
	@NotBlank(message="패스워드를 입력하세요.")
	private String password;
	
	@Size(min=5, max=30, message="이메일 길이가 잘못되었습니다.")
	@Email(message="이메일 양식을 확인하세요.") //이메일 양식에 안맞으면 튕겨나감
	@NotBlank(message="이메일을 입력하세요.")
	private String email;

}
