package com.carrot.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}")
	private String fileRealPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);

		registry.addResourceHandler("/media/**").addResourceLocations("file:///" + fileRealPath).setCachePeriod(3600)
				.resourceChain(true).addResolver(new PathResourceResolver());

	}

	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) { // TODO
	 * Auto-generated method stub registry.addInterceptor(new SessionInterceptor())
	 * .addPathPatterns("/user/profile/**") .addPathPatterns("/post/write/**")
	 * .addPathPatterns("/post/update/**") .addPathPatterns("/post/delete/**"); }
	 */

}
