package com.haojii.prototype.tas.services;

import java.util.Collection;

import com.haojii.prototype.tas.model.User;

public interface IUserService {

	public abstract boolean insertUser(User user);

	public abstract boolean deleteUser(String username);

	public abstract User findUser(String username);

	public abstract boolean updateUser(User user);

	public abstract Collection<User> listUsers();

	public abstract int countUsers();

	public abstract boolean verifyUsernamePassword(User user);

}