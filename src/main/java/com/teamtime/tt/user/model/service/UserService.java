package com.teamtime.tt.user.model.service;

import com.teamtime.tt.user.model.dto.User;

public interface UserService {
	
	/**
	 * 유저 등록
	 * @param user
	 * @return result
	 */
	int insertUser(User user);

	/**
	 * 유저 업데이트
	 * @param user
	 * @return result
	 */
	int updateUser(User user);

}
