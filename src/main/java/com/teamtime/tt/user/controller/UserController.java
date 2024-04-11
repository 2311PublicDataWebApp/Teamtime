package com.teamtime.tt.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService uService;
	
	public UserController(UserService uService) {
		this.uService = uService;
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "/user/login";
	}
	@PostMapping("/login")
	public String userLogin(Model model, User user, HttpSession session) {
		User uOne = uService.selectOneById(user);
		if (uOne != null) {
			session.setAttribute("userId", uOne.getUserId());
			session.setAttribute("userName", uOne.getUserName());
			return "redirect:/";			
		} else {
			return "common/errorPage";
		}
	}
}
