package com.teamtime.tt.user.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService uService;
	private final AlarmService aService;
	private final TeamService tService;
	
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
		List<Team> tList = tService.selectTeamById(userId);
		model.addAttribute("user", user);
		session.setAttribute("tList", tList);
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
		List<Team> tList = tService.selectTeamById(userId);
		model.addAttribute("user", user);
		session.setAttribute("tList", tList);
		session.setAttribute("aList", aList);
		return "/user/myPage";
		
	}
	
	@ResponseBody
	@PostMapping("/updateProfile.do")
	public String updateUserImage(@RequestParam(value="imageFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, @ModelAttribute User user
			, Model model) {
		HttpSession session = request.getSession();
		if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
	       HashMap<String, String> fileMap = com.teamtime.tt.common.SaveAttachedFile.saveFile(uploadFile, request); // 업로드한 파일 저장하고 경로 리턴
	       String filePath = fileMap.get("filePath");
	       String fileRename = fileMap.get("fileName");
	       if(filePath != null && !filePath.equals("")) {
	    	  user.setImageFile(fileRename);
	          session.setAttribute("imageFile", fileRename);
	       }
	    }
		int result = uService.updateUser(user);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
		
	}
	
	@ResponseBody
	@PostMapping("/update.do")
	public String updateUser(HttpServletRequest request
			, User user
			, Model model) {
		int result = uService.updateUser(user);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
		
	}
	
	@GetMapping("/delete.do")
	public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		int result = uService.deleteUser(userId);
		if (result > 0) {
			return "redirect:/user/logout.do";		
		} else {
			return "redirect:/user/main.do";
		}
		
	}
	
}
