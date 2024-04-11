package com.teamtime.tt.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.user.model.dto.User;

@Mapper
public interface UserMapper {

	User selectOneById(User user);

}
