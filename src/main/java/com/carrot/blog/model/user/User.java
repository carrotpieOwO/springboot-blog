package com.carrot.blog.model.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements UserDetails {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private String role; //USER, MANAGER, ADMIN (롤은 대문자로)
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String email, String profile, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}
	
	//username과 password의 Getter도 만들어져야 하는데 우리는 필드명을 username과 password로 만들었고 Lombok도 있어서 안만들어진 것.
	
	/* id username으로 안하고 id로 했으면 아래와 같은 메소드가 추가되어야함.
	 * @Override public String getUsername() { return userId; }
	 */
	
	
	//여러개의 권한을 리턴 : 나중에 admin 있으면 return 해야함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new SimpleGrantedAuthority("ROLE_"+role)); //"ROLE_" 이건 규칙 이걸 붙여줘야 스프링이 롤인지 인식한다.
		return collectors;
	}
	
	//계정이 만료되지 않았는지 체크하여 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 잠겨있는지 체크하여 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		/*
		 * try { if(tryPassword>5) { return false; }else { return true; } }catch{ return
		 * true }
		 */
		return true;
	}
	
	//비밀번호가 만료되었는지 체크하여 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//해당 계정이 활성화되어있는지 체크하여 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
