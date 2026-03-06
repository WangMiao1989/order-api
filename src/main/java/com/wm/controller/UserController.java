package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.requestDto.LoginRequestForm;
import com.wm.responseDto.LoginResponse;
import com.wm.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public LoginResponse login(@RequestBody LoginRequestForm request) {
		return userService.login(request);
	}
}
