package com.teamtime.tt.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService uService;
	
	public UserController(UserService uService) {
		this.uService = uService;
	}
	
	@GetMapping("/login.do")
	public String showLoginForm() {
		return "/user/login";
	}
	
	@GetMapping("/join.do")
	public String showJoinForm() {
		return "/user/join";
	}
	
	@PostMapping("/join.do")
	public String userJoin(User user) {
		System.out.println(user.getUserId());
		int result = uService.insertUser(user);
		if (result > 0) {
			return "redirect:/";			
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/myPage.do")
	public String showMyPageForm(@AuthenticationPrincipal User user
			, Model model) {
		return "/user/myPage";
	}
	
	@GetMapping("/update.do")
	public String showUpdateForm(@AuthenticationPrincipal User user
			, Model model) {
		return "/user/update";
	}
	
	@PostMapping("/update.do")
	public String updateUser(User user) {
		int result = uService.updateUser(user);
		if (result > 0) {
			return "redirect:/";		
		} else {
			return "redirect:/";
		}
	}
	
	
	
}
