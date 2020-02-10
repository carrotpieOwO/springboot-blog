package com.carrot.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class SpringbootBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}
	
	// form:form 을 위한 필터
	// org.springframework.web.filter.HiddenHttpMethodFilter;
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
	    HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
	    return filter;
	}
	
}
