package com.teamtime.tt.calender.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.calender.model.service.CalenderService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/calender")
public class CalenderController {
	
	private final CalenderService cService;
	private final UserService uService;
	private final AlarmService aService;
	
	@GetMapping("/calender.do")
	public String showTeamCalender(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		return "/calender/calender";
	}
}
