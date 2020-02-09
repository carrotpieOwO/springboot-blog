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
public class ReqUpdateDto {
	
	private int id;
	
	@Size(min=7, max=15, message="패스워드는 7~15자까지 입력 가능합니다.")
	@NotBlank(message="패스워드를 입력하세요.")
	private String password;

	//private String pofile;

}
