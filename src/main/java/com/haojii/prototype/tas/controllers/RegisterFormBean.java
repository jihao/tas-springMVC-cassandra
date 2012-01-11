package com.haojii.prototype.tas.controllers;

import com.haojii.prototype.tas.model.User;


public class RegisterFormBean {
	
	private User user;
	private String password2;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	@Override
	public String toString() {
		return "RegisterFormBean [user=" + user + ", password2=" + password2
				+ "]";
	}
	
	
}
