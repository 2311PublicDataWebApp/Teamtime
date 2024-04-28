package com.teamtime.tt.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatRoom {

    private String roomId;
    private String roomName;
    private Set<WebSocketSession> sessions = new HashSet<>();
    // WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoom create(String roomName){
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = roomName;
        return room;
    }
}
