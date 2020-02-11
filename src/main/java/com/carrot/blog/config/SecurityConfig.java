package com.carrot.blog.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.carrot.blog.model.RespCM;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration //메모리에 띄우기
@EnableWebSecurity //필터로 등록 (인터셉터가 가로채야함)
@EnableGlobalMethodSecurity(prePostEnabled=true) //진입 직전에만 실행
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf 걸려있으면 자바스크립트 요청 다 막아버린다. 
		
		//모든 리퀘스트 받기
		http.authorizeRequests()
			.antMatchers("/user/profile/**","/post/write/**","/post/detail/**","/post/update/**","/post/delete/**").authenticated() //얘는 인증 필요하다.
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") 
			//admin 으로 들어가려면 admin 이나 manager role을 가져야함 (and로 하면 둘다 가지고 있어야함)
			.anyRequest().permitAll()//위에애들 말고 나머지는 다 열어주기
		.and()
			.formLogin() //안드로이드앱같은데서는 폼로그인 아님.데이터만던지는것.
			.loginPage("/user/login") //인증안됐으면 무조건 일로가기.
			/*.successHandler(new AuthenticationSuccessHandler() {
				//익명메소드 만들기, 리퀘스트 리스폰스 authentication 도 다 들고잇음. 
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					System.out.println(""); //이동 전에 뭔가 작업할 수 있다. 
					response.sendRedirect("/"); //이거만하면 디폴트석세스유알엘 과 같음
					//여기서 이렇게 안하고 클래스 따로 빼서 해줘도됨 implements AuthenticationSuccessHandler 해서
					
				}
			}); //프로세스디폴트 얘네보다 얘가 좋음. 
*/			
			.loginProcessingUrl("/user/login") //이 주소를 낚아채서 실행하라. post요청만 받음
			//.defaultSuccessUrl("/"); //successHandler로 사용할 수도 있음. 로그인 성공하면 이곳으로 이동해라. //근데 이거 하면 제이슨으로 보내는게 아니어서 ajax 못씀
			.successHandler(new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					
					
					PrintWriter out = response.getWriter();
					//제이슨으로 바꿔 보내주기
					ObjectMapper mapper = new ObjectMapper(); //objectMapper -> 지선처럼 자바오브젝트 제이슨으로 바꿔주는거
					
					String jsonString = mapper.writeValueAsString(new RespCM(200,"ok"));
					System.out.println(jsonString);
					
					out.print(jsonString);
					out.flush();
					
				}
			});
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//로그인할때 암호화 다시 풀어주기 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encode());
	}

}
