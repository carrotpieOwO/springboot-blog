package com.carrot.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface RespCode {
	int 아이디중복 = -2;
	int 오류 = -1;
	int 성공 = 1;
	int 무반응 = 0;

}
