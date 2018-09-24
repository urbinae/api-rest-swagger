/**
 * 
 */
package com.interfell.apidoc.database;

import java.util.ArrayList;
import java.util.List;

import com.interfell.apidoc.models.User;

/**
 * @author eimar
 *
 */
public class UserList {

	/**
	 * 
	 */
	public List<User> userList;
	public UserList() {
		this.userList = new ArrayList<User>();
		
	}
	
	public UserList(List<User> list) {
		this.userList = list;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	

}
