package com.carrot.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.carrot.blog.aop.SessionInterceptor;

//web.xml 파일을 자바파일로 바꾼거 configuration 이랑 webmvcconfigurer 붙이기

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}")
	private String fileRealPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);

		// 파일 경로 인식하게 하기
		registry.addResourceHandler("/media/**").addResourceLocations("file:///" + fileRealPath).setCachePeriod(3600)
				.resourceChain(true).addResolver(new PathResourceResolver());
		// 이미지 받은지 얼마 안됫는데 또 받을 때 다운안받고 캐쉬쓰게하기- setCachePeriod 이거 제외하곤 필수
		//여기서 핸들러 경로..정도만 알고잇으면됨

	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
		//여기 적힌 주소는 인터셉터를 탄다.
			.addPathPatterns("/user/profile/**")
			.addPathPatterns("/post/write/**")
			.addPathPatterns("/post/update/**")
			.addPathPatterns("/post/delete/**");
		//.addExcludePatterns() 는 제외시킬 때 사용
	}

}
