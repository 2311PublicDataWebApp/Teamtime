package com.teamtime.tt.team.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.chat.model.dto.ChatRoom;
import com.teamtime.tt.chat.model.repo.ChatRoomRepository;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
	
	private final UserService uService;
	private final TeamService tService;
	private final AlarmService aService;
	private final ChatRoomRepository repository;

	@GetMapping("/main.do")
    public String showTeamMain(@AuthenticationPrincipal UserDetails userDetails
            , HttpSession session
            , Model model
            , Integer teamNo) {
        String userId = userDetails.getUsername();
        User user = uService.selectUserById(userId);
        List<UserJoinTeam> tList = tService.selectTeamById(userId);
        List<Alarm> aList = aService.selectUnreadAlarm(userId);
        model.addAttribute("user", user);
        session.setAttribute("tList", tList);
        session.setAttribute("aList", aList);
        List<User> uList = tService.selectUsersInTeam(teamNo);
        model.addAttribute("uList", uList);
        return "/team/teamMain";
    }
	
	@GetMapping("/insert.do")
	public String showInsertTeam(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<UserJoinTeam> tList = tService.selectTeamById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("tList", tList);
			session.setAttribute("aList", aList);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
		return "/team/insertTeam";
	}
	
	@PostMapping("/insert.do")
	public String InsertTeam(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, String teamName
			, String[] userIds) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			ChatRoom room = ChatRoom.create(teamName);
			Team team = new Team();
			team.setUserId(userId);
			team.setTeamName(teamName);
			team.setRoomId(room.getRoomId());
			int result = tService.insertTeam(team);
			int result2 = repository.insertChatRoom(room);
			int result3 = repository.insertChatMember(room, userId);
			if (result > 0) {
				if (userIds != null) {
					for (String userIdOne : userIds) {
						int result4 = tService.insertUserTeam(userIdOne);
						int result5 = repository.insertChatMember(room, userIdOne);
					}					
				}
				if (userId != null) {
					int result6 = tService.insertUserTeam(userId);					
				}
				List<UserJoinTeam> tList = tService.selectTeamById(userId);
				session.setAttribute("tList", tList);
				return "/team/teamMain";				
			} else {
				model.addAttribute("msg", "입력 실패");
				return "/common/error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@GetMapping("/update.do")
	public String showUpdateTeam(@AuthenticationPrincipal UserDetails userDetails
            , HttpSession session
            , Model model
            , Integer teamNo) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<UserJoinTeam> tList = tService.selectTeamById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("tList", tList);
			session.setAttribute("aList", aList);
			List<User> uList = tService.selectUsersInTeam(teamNo);
			if (uList != null) {
				model.addAttribute("teamNo", tService.selectTeamByNo(teamNo).getTeamNo());
				model.addAttribute("teamName", tService.selectTeamByNo(teamNo).getTeamName());
				model.addAttribute("teamManager", tService.selectTeamByNo(teamNo).getUserId());
				model.addAttribute("uList", uList);
				return "/team/updateTeam";
			} else {
				model.addAttribute("msg", "수정 실패");
				return "/common/error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@PostMapping("/update.do")
	public String updateTeam(@AuthenticationPrincipal UserDetails userDetails
            , HttpSession session
            , Model model
            , Integer teamNo
            , String teamName
			, String[] userIds) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			
			// 멤버 초기화
			int result = tService.deleteUsersInTeam(teamNo);
			
			Team team = new Team();
			team.setTeamNo(teamNo);
			team.setTeamName(teamName);
			int result2 = tService.updateTeam(team);
			if (result2 > 0) {
				if (userIds != null) {
					for (String userIdOne : userIds) {
						UserJoinTeam ujt = new UserJoinTeam(userIdOne, teamNo);
						int result4 = tService.insertUserTeam(ujt);
					}					
				}
				if (userId != null) {
					UserJoinTeam ujt = new UserJoinTeam(userId, teamNo);
					int result6 = tService.insertUserTeam(ujt);					
				}
				List<UserJoinTeam> tList = tService.selectTeamById(userId);
				session.setAttribute("tList", tList);
				return "/team/teamMain";				
			} else {
				List<UserJoinTeam> tList = tService.selectTeamById(userId);
				session.setAttribute("tList", tList);
				model.addAttribute("msg", "입력 실패");
				return "/common/error";
			}
		} catch (Exception e) {
			String userId = userDetails.getUsername();
	        User user = uService.selectUserById(userId);
	        List<UserJoinTeam> tList = tService.selectTeamById(userId);
	        List<Alarm> aList = aService.selectUnreadAlarm(userId);
	        model.addAttribute("user", user);
	        session.setAttribute("tList", tList);
	        session.setAttribute("aList", aList);
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@GetMapping("/deleteUser.do")
	public String deleteUserInTeam(@AuthenticationPrincipal UserDetails userDetails
            , HttpSession session
            , Model model
            , Integer teamNo) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			UserTeam ut = new UserTeam(teamNo, userId);
			int result = tService.deleteUserInTeam(ut);
			List<UserJoinTeam> tList = tService.selectTeamById(userId);
			session.setAttribute("tList", tList);
			if (result > 0) {
				return "index";
			} else {
				model.addAttribute("msg", "삭제 실패");
				return "/common/error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@GetMapping("/deleteTeam.do")
	public String deleteTeam(@AuthenticationPrincipal UserDetails userDetails
            , HttpSession session
            , Model model
            , Integer teamNo) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			int result = tService.deleteTeam(teamNo);
			List<UserJoinTeam> tList = tService.selectTeamById(userId);
			session.setAttribute("tList", tList);
			if (result > 0) {
				return "index";
			} else {
				model.addAttribute("msg", "삭제 실패");
				return "/common/error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@ResponseBody
	@PostMapping("/checkUser.do")
	public String checkUser(String userId) {
		User user = uService.selectUserById(userId);
		if (user != null) {
			return "success";
		} else {
			return "fail";
		}
	}
}
