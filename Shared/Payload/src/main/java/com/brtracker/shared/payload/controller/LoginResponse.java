package com.brtracker.shared.payload.controller;

import com.brtracker.shared.payload.controller.data.User;


public class LoginResponse  {
	
	private boolean login;
	private String message;
	private User user;
	
	
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
