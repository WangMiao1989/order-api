package com.wm.service;

import com.wm.requestDto.LoginRequestForm;
import com.wm.responseDto.LoginResponse;

public interface UserService {
	public LoginResponse login(LoginRequestForm request);
	
	public void logout(String userId);
}
