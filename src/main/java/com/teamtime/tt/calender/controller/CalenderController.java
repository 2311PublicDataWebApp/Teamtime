package com.teamtime.tt.calender.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.calender.model.dto.Calender;
import com.teamtime.tt.calender.model.service.CalenderService;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.todo.model.dto.Todo;
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
	private final TeamService tService;
	
	// 팀 캘린더 메인
	@GetMapping("/calender.do")
	public String showTeamCalender(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, @RequestParam("teamNo") Integer teamNo) {
		System.out.println(teamNo);
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		List<UserJoinTeam> tList = tService.selectTeamById(userId);
		List<Team> leader  = cService.selectUserByTeam(teamNo);
		if(!leader.isEmpty()) {
			List<UserTeam> users = cService.selectUsersByTeam(teamNo);
			System.out.println(users);
			model.addAttribute("users", users);
		}
		List<Calender> cList = cService.selectListByNo(teamNo);
		System.out.println(cList);
		model.addAttribute("user", user);
		model.addAttribute("teamNo", teamNo);
		model.addAttribute("leader", leader);
		model.addAttribute("cList", cList);
		session.setAttribute("tList", tList);
		session.setAttribute("aList", aList);
		return "/calender/calender";
	} 
	
	// 팀 일정 등록 기능 
	@ResponseBody
	@PostMapping("/insert.do")
	public String insertCalender(Calender calender
			, @RequestParam("teamNo") Integer teamNo
			, @RequestParam("title") String title
			, @RequestParam("start") Date start
			, @RequestParam("end") Date end
			, @RequestParam("color") String color
			, @AuthenticationPrincipal UserDetails userDetails) {
		String userId = userDetails.getUsername();
		calender.setUserId(userId);
		calender.setTeamNo(teamNo);
		Date startDate = start;
		Date endDate = end;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String FormatStart = format.format(startDate);
		String FormatEnd = format.format(endDate);
		System.out.println(FormatStart);
		System.out.println(FormatEnd);
		calender.setStartDate(FormatStart);
		calender.setEndDate(FormatEnd);
		calender.setCalenderContent(title);
		int result = cService.insertCalender(calender);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	// 팀 일정 삭제 기능
	@ResponseBody
	@GetMapping("/delete.do")
	public String deleteCalender(@RequestParam("calenderNo") Integer calenderNo) {
		System.out.println(calenderNo);
		int result = cService.deleteByNo(calenderNo);
		if(result > 0) {
            return "success";
        } else {
            return "fail";
        }
	}
	
	// 캘린더에 할 일 목록 가져오기
	@ResponseBody
	@GetMapping("/selectCalender.do")
	public List<Calender> selectTodo(@RequestParam("teamNo") Integer teamNo) {
	    List<Calender> cList = cService.selectTodoByTeam(teamNo);
	    return cList;
	}
	
	@ResponseBody
	@PostMapping("/update.do")
	public String updateCalender(Calender calender
			, @RequestParam("calenderNo") Integer calenderNo
			, @RequestParam("start") Date start
			, @RequestParam("end") Date end) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formatStart = format.format(start);
	    String formatEnd = format.format(end);
		calender.setStartDate(formatStart);
		calender.setEndDate(formatEnd);
		int result = cService.modifyCalender(calender);
		return "";
	}
}
