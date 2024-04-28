package com.teamtime.tt.chat.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.chat.model.dto.ChatRoom;
import com.teamtime.tt.user.model.dto.User;

@Mapper
public interface ChatMapper {

	/**
	 * 채팅방 등록
	 * @param name
	 * @return result
	 */
	int insertChatRoom(ChatRoom chatRoom);
	
	
}
