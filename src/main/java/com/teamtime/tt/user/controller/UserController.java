package com.teamtime.tt.user.controller;

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
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService uService;
	private AlarmService aService;
	
	public UserController(UserService uService, AlarmService aService) {
		this.uService = uService;
		this.aService = aService;
	}
	
	@GetMapping("/login.do")
	public String showLoginForm() {
		return "/user/login";
	}
	
	@GetMapping("/join.do")
	public String showJoinForm() {
		return "/user/join";
	}
	
	@GetMapping("/main.do")
	public String showMainForm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		return "index";
	}
	
	@PostMapping("/join.do")
	public String userJoin(User user) {
		System.out.println(user.getUserId());
		int result = uService.insertUser(user);
		if (result > 0) {
			return "redirect:/user/login.do";			
		} else {
			return "redirect:/user/login.do";
		}
	}
	
	@GetMapping("/myPage.do")
	public String showMyPageForm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		return "/user/myPage";
	}
	
	@GetMapping("/update.do")
	public String showUpdateForm(@AuthenticationPrincipal UserDetails userDetails, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		return "/user/update";
	}
	
	@PostMapping("/update.do")
	public String updateUser(User user) {
		int result = uService.updateUser(user);
		if (result > 0) {
			return "redirect:/user/myPage.do";		
		} else {
			return "redirect:/user/main.do";
		}
	}
	
	@GetMapping("/delete.do")
	public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
//		UserDetails userDetails = (UserDetails)principal;
//		userDetails.getUser();
		String userId = userDetails.getUsername();
		int result = uService.deleteUser(userId);
		if (result > 0) {
			return "redirect:/user/logout.do";		
		} else {
			return "redirect:/user/main.do";
		}
	}
	
}
