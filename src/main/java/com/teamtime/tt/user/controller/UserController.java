package com.teamtime.tt.user.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.chat.model.dto.ChatRoom;
import com.teamtime.tt.chat.model.repo.ChatRoomRepository;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
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
	private final ChatRoomRepository repository;
	
	@Value("${profile.imglocation}")
	private String root;
	
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
		List<UserJoinTeam> tList = tService.selectTeamById(userId);
		List<ChatRoom> cList = repository.selectAllRooms(userId);
		model.addAttribute("user", user);
		session.setAttribute("cList", cList);
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
		List<UserJoinTeam> tList = tService.selectTeamById(userId);
		model.addAttribute("user", user);
		session.setAttribute("tList", tList);
		session.setAttribute("aList", aList);
		return "/user/myPage";
		
	}
	
	@ResponseBody
	@PostMapping("/updateProfile.do")
	public String updateUserImage(@RequestParam(value="imageFile", required=false) MultipartFile uploadFile
			, @RequestParam(value = "userId") String userId
			, HttpServletRequest request
			, Model model) {
		HttpSession session = request.getSession();
		User user = uService.selectUserById(userId);
		if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
	       Map<String, String> fileMap = this.saveFile(uploadFile, request); // 업로드한 파일 저장하고 경로 리턴
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
	
	@ResponseBody
	@GetMapping("/delete.do")
	public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		int result = uService.deleteUser(userId);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
		
	}
	
	private Map<String, String> saveFile(MultipartFile file , HttpServletRequest request) {
		String savePath = root + "/uUploadFiles";
		Map<String, String> fileMap = new HashMap<String, String>();
//		String root = request.getSession().getServletContext().getRealPath("images");
//		String savePath = root + "\\uUploadFiles";
		File saveFolder = new File(savePath);
		if (!saveFolder.exists())
			saveFolder.mkdir();
		String originalFileName = file.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + ext;
//		File saveFile = new File(savePath + "\\" + renameFileName);
//		file.transferTo(saveFile); 
//		fileMap.put("filePath", "../resources/uUploadFiles/" + renameFileName);
//		fileMap.put("fileName", renameFileName);
		try {
			File saveFile = new File(savePath + "/" + renameFileName);
			file.transferTo(saveFile); 
			fileMap.put("filePath", "../resources/uUploadFiles/" + renameFileName);
			fileMap.put("fileName", renameFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileMap;
	}
	
	public void deleteFile(String filePath, HttpServletRequest request) {
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			// 파일이 존재하면 파일 삭제
			deleteFile.delete();
		}
	}
	
}
