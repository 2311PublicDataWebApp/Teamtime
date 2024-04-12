package com.teamtime.tt.user.model.service.logic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.mapper.UserMapper;
import com.teamtime.tt.user.model.service.UserService;

@Service
public class UserServiceLogic implements UserService {
	
	private UserMapper uMapper;
	private BCryptPasswordEncoder encoder;
	
	public UserServiceLogic(UserMapper uMapper, BCryptPasswordEncoder encoder) {
		this.uMapper = uMapper;
		this.encoder = encoder;
	}

	/**
	 * 유저 등록
	 */
	@Override
	public int insertUser(User user) {
		String password = encoder.encode(user.getUserPw());
		user.setUserPw(password);
		int result = uMapper.insertUser(user);
		return result;
	}

	/**
	 * 유저 업데이트
	 */
	@Override
	public int updateUser(User user) {
		int result = uMapper.updateUser(user);
		return result;
	}

}
