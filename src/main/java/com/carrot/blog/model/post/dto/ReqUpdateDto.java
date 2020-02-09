package com.carrot.blog.model.post.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqUpdateDto {
	private int id;
	
	@NotBlank(message="타이틀을 입력하세요")
	private String title;
	
	@NotBlank(message="내용을 입력하세요.")
	private String content;
	

}
