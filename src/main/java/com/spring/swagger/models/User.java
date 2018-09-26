package com.spring.swagger.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table
public class User{

	private static final long serialVersionUID = 1L;
	
	@Id
	@ApiModelProperty(notes = "Email del usuario")
	private String email;
	
	@ApiModelProperty(notes = "Contraseña del usuario")
	private String password;
	
	public User() {
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
