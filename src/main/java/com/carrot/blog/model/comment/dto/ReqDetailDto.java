package com.carrot.blog.model.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqDetailDto {
	private int id; //key 값 반환을 위해
	private int userId;
	private int postId;
	private String content;
}
