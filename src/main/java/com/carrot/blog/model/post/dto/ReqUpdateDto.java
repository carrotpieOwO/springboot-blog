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
	
	private String title;
	
	private String content;
	

}
