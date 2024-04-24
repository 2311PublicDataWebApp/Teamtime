package com.teamtime.tt.user.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserMapper uMapper;
	
	public CustomUserDetailsService(UserMapper uMapper) {
		this.uMapper = uMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("Logging in .. ..");
		User user = uMapper.selectUserById(userId);
		if (user != null) {
			return new CustomUserDetails(user);
		}
		return null;
	}

}