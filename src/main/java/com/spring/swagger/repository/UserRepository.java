package com.spring.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.swagger.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
