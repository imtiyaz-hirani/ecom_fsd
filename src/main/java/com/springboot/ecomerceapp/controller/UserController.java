package com.springboot.ecomerceapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomerceapp.model.User;
import com.springboot.ecomerceapp.service.UserService;

@RestController
public class UserController {

	/* 
	 * If username/password given are correct, them spring will call this api method. 
	 * so we need to request spring to tell us the username of the logged in user. 
	 * We use Principal interface for reading loggedin username value. 
	 * */
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
	public User login(Principal principal) {
		String username = principal.getName();
		User user  = userService.getUserByUserName(username);
		return user; 
	}
}
