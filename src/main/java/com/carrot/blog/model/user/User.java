package com.carrot.blog.model.user;

import java.sql.Timestamp;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	public User(String username, String password, String email, String profile, Timestamp createDate) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.createDate = createDate;
	}
	
	public User(int id, String username, String password, String email, String profile, Timestamp createDate) {
		System.out.println("user(all)호출됨");
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.createDate = createDate;
	}

	


	public User() {
		System.out.println("user()호출됨");
	}




	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		System.out.println("setusername  호출됨");
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getProfile() {
		return profile;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}
	
	
	
}
