package com.wm.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.UserInfoEntity;
import com.wm.requestDto.LoginRequestForm;
import com.wm.responseDto.LoginResponse;
import com.wm.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public LoginResponse login(@RequestBody LoginRequestForm request) {
		return userService.login(request);
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest reqest) {
		UserInfoEntity userInfo = (UserInfoEntity)reqest.getAttribute("userInfo");
		if(!Objects.isNull(userInfo)) {
			userService.logout(userInfo.getUserId());
		}
	}
}
