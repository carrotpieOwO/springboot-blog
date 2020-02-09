package com.carrot.blog.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLoginDto {
	
	@Size(min=7, max=15, message="유저네임은 7~15자까지 입력 가능합니다.")
	@NotBlank(message="유저네임을 입력하세요")
	@Pattern(regexp = "^[a-z0-9_]{4,20}$", message = "영문,숫자만 입력가능합니다.")
	private String username;
	
	@Size(min=7, max=15, message="패스워드는 7~15자까지 입력 가능합니다.")
	@NotBlank(message="패스워드를 입력하세요.")
	private String password;
	

}
