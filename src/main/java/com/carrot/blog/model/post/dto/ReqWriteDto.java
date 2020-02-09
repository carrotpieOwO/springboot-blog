package com.carrot.blog.model.post.dto;

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
public class ReqWriteDto {
	
	@NotBlank(message="타이틀을 입력하세요")
	private String title;
	
	@NotBlank(message="내용을 입력하세요.")
	private String content;
	
	private int userId;

}
