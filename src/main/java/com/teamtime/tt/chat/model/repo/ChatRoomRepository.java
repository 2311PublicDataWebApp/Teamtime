package com.teamtime.tt.chat.model.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtime.tt.chat.model.dto.ChatMember;
import com.teamtime.tt.chat.model.dto.ChatRoom;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

	@Autowired
	private SqlSession session;
	private Map<String, ChatRoom> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> selectAllRooms(String userId){
//    	채팅방 생성 순서 최근 순으로 반환
//      Collections.reverse(result);
    	List<ChatRoom> result = new ArrayList<>(chatRoomDTOMap.values());
    	List<ChatRoom> cList = session.selectList("ChatMapper.selectAllRooms", userId);
        return cList;
    }

    public ChatRoom selectRoomById(String roomId){
    	ChatRoom chatRoom = session.selectOne("ChatMapper.selectRoomById", roomId);
        return chatRoom;
    }
    
    public int insertChatRoom(String name, String userId){
    	int result = 0;
        ChatRoom room = ChatRoom.create(name);
//      chatRoomDTOMap.put(room.getRoomId(), room);
        result += session.insert("ChatMapper.insertChatRoom", room);
        ChatMember chatMember = new ChatMember();
    	chatMember.setRoomId(room.getRoomId());
    	chatMember.setUserId(userId);
    	result += session.insert("ChatMapper.insertChatMember", chatMember);
        return result;
    }
    
}
