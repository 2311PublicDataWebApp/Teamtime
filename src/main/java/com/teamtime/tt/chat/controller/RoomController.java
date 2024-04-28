package com.teamtime.tt.chat.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.chat.model.dto.ChatRoom;
import com.teamtime.tt.chat.model.mapper.ChatMapper;
import com.teamtime.tt.chat.model.repo.ChatRoomRepository;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class RoomController {

	private final UserService uService;
	private final AlarmService aService;
	private final TeamService tService;
	private final ChatMapper cMapper;
    private final ChatRoomRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public String showRoomsForm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, Model model) {
        log.info("# All Chat Rooms");
        String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		List<Team> tList = tService.selectTeamById(userId);
		List<ChatRoom> cList = repository.selectAllRooms(userId);
//		model.addAttribute("list", repository.findAllRooms());
		model.addAttribute("user", user);
		model.addAttribute("cList", cList);
		session.setAttribute("tList", tList);
		session.setAttribute("aList", aList);
        return "/chat/rooms";
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String insertChatRoom(@AuthenticationPrincipal UserDetails userDetails
    		, @RequestParam(value = "name") String name
    		, RedirectAttributes rttr
    		, Model model) {
        log.info("# Create Chat Room , name: " + name);
        String userId = userDetails.getUsername();
        int result = repository.insertChatRoom(name, userId);
        if (result > 0) {
        	rttr.addFlashAttribute("roomName", name);
        } else {
        	return "/common/error";
        }
        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public String showRoomForm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, String roomId
			, Model model) {
        log.info("# get Chat Room, roomID : " + roomId);
        String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		List<Team> tList = tService.selectTeamById(userId);
		ChatRoom chatRoom = repository.selectRoomById(roomId);
		model.addAttribute("room", chatRoom);
		model.addAttribute("user", user);
		session.setAttribute("tList", tList);
		session.setAttribute("aList", aList);
        return "/chat/room";
    }
}