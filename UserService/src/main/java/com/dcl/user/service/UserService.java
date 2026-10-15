package com.dcl.user.service;
import java.util.List;

import com.dcl.user.dto.UserDto;
import com.dcl.user.request.RegisterRequest;
import com.dcl.user.request.LoginRequest;


public interface UserService {
	
	UserDto register(RegisterRequest request);
	
	UserDto login(LoginRequest request);
	
	UserDto getUserById(Integer userId);
	
//	List<UserDto> gettAllUsers();
	
	void deleteByUserId(Integer userId);
	
}
	