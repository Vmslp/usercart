package com.security.UserAuthMicroservices.service;

import java.util.List;

import com.security.UserAuthMicroservices.entity.UserInfo;

public interface UserInfoService {
	
	String addUser(UserInfo userInfo);

	List<UserInfo> getallUsers();

}
