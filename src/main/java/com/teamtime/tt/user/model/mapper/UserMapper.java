package com.teamtime.tt.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.user.model.dto.User;

@Mapper
public interface UserMapper{

	/**
	 * 유저 찾기
	 * @param userId
	 * @return user
	 */
	User selectUserById(String userId);

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

	/**
	 * 유저 삭제
	 * @param userId
	 * @return result
	 */
	int deleteUser(String userId);

}
