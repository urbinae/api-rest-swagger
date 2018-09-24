package com.interfell.apidoc.repository;

import java.util.ArrayList;
import java.util.List;

import com.interfell.apidoc.database.UserList;
import com.interfell.apidoc.models.User;

public class UserListRepository implements IUserListRepository {

	public UserList userList;

	public UserListRepository() {
		this.userList = new UserList();
	}
	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	@Override
	public void delete(String email) {
		userList.getUserList();
		
	}

	@Override
	public User save(User user) {
		Boolean band = userList.getUserList().add(user);
		if(!band)
			return null;
		return user;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		User u = new User();
		u.setEmail("email");
		u.setPassword("123l");
		userList.getUserList().add(u);
			return userList.getUserList();
	}

	@Override
	public User getById(String email) {
		ArrayList<User> uList = (ArrayList<User>) userList.getUserList();
		
		for (User user : uList) {
	        if (user.getEmail().equals(email)){
	            return user;
	        }else{
	            return null;  
	       } 
	    }
		return null;
	}

	@Override
	public Boolean existsById(String email) {
		if (getById(email) != null) 
			return true;
		else 
			return false;
	}

}
