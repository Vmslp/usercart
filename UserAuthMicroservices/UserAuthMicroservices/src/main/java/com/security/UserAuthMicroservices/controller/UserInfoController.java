package com.security.UserAuthMicroservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.UserAuthMicroservices.entity.AuthRequest;
import com.security.UserAuthMicroservices.entity.UserInfo;
import com.security.UserAuthMicroservices.service.JwtService;
import com.security.UserAuthMicroservices.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/new")
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
		String userAdded = userService.addUser(userInfo);
		return new ResponseEntity<String>(userAdded, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserInfo>> getallUsers() {
		List<UserInfo> getallUsers = userService.getallUsers();
		return new ResponseEntity<List<UserInfo>>(getallUsers, HttpStatus.CREATED);
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid user...!");
		}

	}
}
