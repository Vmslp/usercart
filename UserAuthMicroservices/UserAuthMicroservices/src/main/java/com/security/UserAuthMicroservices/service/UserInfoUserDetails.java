package com.security.UserAuthMicroservices.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.UserAuthMicroservices.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoUserDetails(UserInfo userInfo) {
		name = userInfo.getUsername();
		password = userInfo.getPassword();
		authorities = Arrays.stream(userInfo.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

//    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, Object> claims) {
//        List<String> roles = (List<String>) claims.get("roles");
//        return roles.stream()
//                   .map(SimpleGrantedAuthority::new)
//                   .collect(Collectors.toList());
//    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
