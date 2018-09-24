package com.interfell.apidoc.repository;

import java.util.List;

import com.interfell.apidoc.models.User;

public interface IUserListRepository {
	
	public void delete(String email);
	public User save(User user);
	public User update(User user);
	public List<User> getAll();
	public User getById(String email);
	public Boolean existsById(String email);
	

}
