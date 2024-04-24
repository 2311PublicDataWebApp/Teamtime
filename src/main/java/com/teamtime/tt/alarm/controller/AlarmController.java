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

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	
	@Autowired
	private AlarmService aService;
	@Autowired
	private UserService uService;
	
	// 전체 알람 리스트
	@GetMapping("/myAlarm.do")
	public String showAlarmList(@AuthenticationPrincipal UserDetails userDetails
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectAllAlarm(userId);
		model.addAttribute("user", user);
		model.addAttribute("aList", aList);
		return "/alarm/myAlarm";
	}
	
	// 읽지 않은 알람 가져오기
	@ResponseBody
	@PostMapping("/unreadAlarm.do")
	public String showUnreadAlarm(@AuthenticationPrincipal UserDetails userDetails
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> unreadAlarmList = aService.selectUnreadAlarm(userId);
		model.addAttribute("unreadAlarmList", unreadAlarmList);
		if(!unreadAlarmList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			return gson.toJson(unreadAlarmList);
		}
		return "";
	}
	
	// 전체 알람 읽기
	@ResponseBody
	@PostMapping("/readAllAlarm.do")
	public String readAllAlarm(@AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		int result = aService.updateAllAlarmIsRead(userId);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 전체 알람 삭제
	@GetMapping("/deleteAllAlarmFromList.do")
	public String deleteAllAlarmFromList(@AuthenticationPrincipal UserDetails userDetails
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		int result = aService.deleteAllAlarm(userId);
		return "redirect:/alarm/myAlarm.do";
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
