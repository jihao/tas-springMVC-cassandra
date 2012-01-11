package com.haojii.prototype.tas.controllers;

import com.haojii.prototype.tas.model.User;


public class LoginFormBean {
	
	private User user;
	private boolean remember;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	@Override
	public String toString() {
		return "LoginFormBean [user=" + user + ", remember=" + remember + "]";
	}
	
}
