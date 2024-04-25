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
import com.teamtime.tt.team.model.dto.Team;
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

	@GetMapping("/insert.do")
	public String showInsertTeam(@AuthenticationPrincipal UserDetails userDetails, HttpSession session, Model model) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/team/insertTeam";
	}
	
	@PostMapping("/insert.do")
	public String InsertTeam(@AuthenticationPrincipal UserDetails userDetails
			, String teamName
			, String[] userIds) {
		try {
			String userId = userDetails.getUsername();
			Team team = new Team();
			team.setUserId(userId);
			team.setTeamName(teamName);
			Integer teamNo = tService.insertTeam(team);
			if (teamNo != null && userIds != null) {
				for (String userIdOne : userIds) {
					UserTeam userTeam = new UserTeam(teamNo, userIdOne);
					int result = tService.insertUserTeam(userTeam);
				}
			}
		} catch (Exception e) {
			
		}
		return "/team/insertTeam";
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
