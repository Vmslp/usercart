package com.security.UserAuthMicroservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.UserAuthMicroservices.entity.UserInfo;
import com.security.UserAuthMicroservices.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	boolean flag;

	@Override
	public String addUser(UserInfo userInfo) {
		List<UserInfo> userList = userInfoRepository.findAll();
		
		
		if(null != userList) {
			for(UserInfo user : userList) {
				if(user.getUsername().equals(userInfo.getUsername()))
					flag =true;
				else
					flag = false;
			}
		}
		if(flag) {
			return "user already exists";
		}else {
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			userInfoRepository.save(userInfo);
			return "Added Successfully into DB...";
		}
		
	}

	@Override
	public List<UserInfo> getallUsers() {
		return userInfoRepository.findAll();
	}
}






























//userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//userInfoRepository.save(userInfo);
//return "user added successfully...!!!";