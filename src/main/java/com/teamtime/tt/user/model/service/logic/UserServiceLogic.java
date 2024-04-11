package com.teamtime.tt.user.model.service.logic;

import org.springframework.stereotype.Service;

import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.mapper.UserMapper;
import com.teamtime.tt.user.model.service.UserService;

@Service
public class UserServiceLogic implements UserService {
	
	private UserMapper uMapper;
	
	public UserServiceLogic(UserMapper uMapper) {
		this.uMapper = uMapper;
	}

	@Override
	public User selectOneById(User user) {
		User uOne = uMapper.selectOneById(user);
		return uOne;
	}

}
