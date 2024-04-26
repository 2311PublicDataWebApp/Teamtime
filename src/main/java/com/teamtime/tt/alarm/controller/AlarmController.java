package com.teamtime.tt.alarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	
	@Autowired
	private AlarmService aService;
	@Autowired
	private UserService uService;
	
	// 읽지 않은 알람 가져오기
	@ResponseBody
	@PostMapping("/printUnreadAlarm.do")
	public List<Alarm> showUnreadAlarm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> unreadAlarmList = aService.selectUnreadAlarm(userId);
		model.addAttribute("aList", unreadAlarmList);
		return unreadAlarmList;
	}
	
	// 선택 알람 삭제
	@ResponseBody
	@PostMapping("/deleteAlarmByNo.do")
	public String deleteAlarmByNo(String alarmNo) {
		int result = aService.deleteAlarmByNo(alarmNo);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 알람 센터 모든 알람 삭제
	@ResponseBody
	@PostMapping("/deleteAllAlarm.do")
	public String deleteAllAlarm(String userId) {
		int result = aService.deleteAllAlarm(userId);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}
