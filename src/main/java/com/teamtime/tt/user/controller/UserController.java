package com.teamtime.tt.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
	public String showMyPageForm(@AuthenticationPrincipal UserDetails userDetails
			,Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		model.addAttribute("user", user);
		return "/user/myPage";
	}
	
	@GetMapping("/update.do")
	public String showUpdateForm() {
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
	
	@GetMapping("/delete.do")
	public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
//		UserDetails userDetails = (UserDetails)principal;
//		userDetails.getUser();
		String userId = userDetails.getUsername();
		int result = uService.deleteUser(userId);
		if (result > 0) {
			return "redirect:/user/logout.do";		
		} else {
			return "redirect:/";
		}
	}
	
}
