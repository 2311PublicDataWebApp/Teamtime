package com.teamtime.tt.vote.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;
import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.service.VoteService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
	
	private final VoteService vService;
	private final UserService uService;
	private final AlarmService aService;
	private final TeamService tService;
	
	@GetMapping("/list.do")
	public String showVoteList(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Team> tList = tService.selectTeamById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		session.setAttribute("tList", tList);
		return "/vote/voteList";
	}
	
	@GetMapping("/insert.do")
	public String showInsertVote(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Team> tList = tService.selectTeamById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		session.setAttribute("tList", tList);
		return "/vote/insertVote";
	}
	
	@PostMapping("/insert.do")
	public String InsertVote(@AuthenticationPrincipal UserDetails userDetails
			, Model model
			, Vote vote
			, String[] voteOptions) {
		try {
			String userId = userDetails.getUsername();
			System.out.println("투표 값 확인 좌표");
		} catch (Exception e) {
			
		}
		return "";
	}

}
