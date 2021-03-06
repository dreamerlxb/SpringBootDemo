package com.lxb.demo.security;

import java.io.Serializable;

public class JwtAuthRequest implements Serializable {

	private static final long serialVersionUID = -8445943548965154778L;

	private String username;
	private String password;

	public JwtAuthRequest() {
		super();
	}

	public JwtAuthRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthRequest [username=" + username + ", password=" + password + "]";
	}
}
