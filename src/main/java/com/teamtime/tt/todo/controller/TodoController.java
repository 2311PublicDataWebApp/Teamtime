package com.teamtime.tt.todo.controller;




import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.todo.model.dto.Todo;
import com.teamtime.tt.todo.model.service.TodoService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
	
	private final TodoService tService;
	private final UserService uService;
	private final AlarmService aService;
	
	// 나의투두 페이지 이동
	@GetMapping("/myTodo.do")
	public String showMyTodo(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {

	    // 현재 날짜 가져오기(2024년 4월 4주차)
	    Calendar calendar = Calendar.getInstance();
	    int currentYear = calendar.get(Calendar.YEAR);
	    int currentMonth = calendar.get(Calendar.MONTH) + 1;
	    int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
	    model.addAttribute("currentYear", currentYear);
	    model.addAttribute("currentMonth", currentMonth);
	    model.addAttribute("currentWeek", currentWeek);

	    // 해당 주차 기간 가져오기(2024-04-22 ~ 2024-04-28)
	    SimpleDateFormat sdf = new SimpleDateFormat("M/d");
	    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    for (int i = 0; i < 7; i++) {
	        String weekDate = sdf.format(calendar.getTime());
	        String weekDay = weekDate.substring(2, 3);
	        model.addAttribute("weekDay" + (i + 1), weekDay);
	        model.addAttribute("weekDate" + (i + 1), weekDate);
	        System.out.println(weekDay);
	        calendar.add(Calendar.DATE, 1);
	    }

	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
	    List<Todo> toList = tService.selectTodoById(userId);

	    // 날짜 형변환
	    LocalDate today = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String todayAsString = today.format(formatter);
	    LocalDate tomorrow = today.plusDays(1);
	    String tomorrowAsString = tomorrow.format(formatter);

	    // 해당 주차의 시작일과 종료일 계산
	    LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	    LocalDate endOfWeek = startOfWeek.plusDays(6);
	    String dateStart = startOfWeek.format(formatter);
	    String dateEnd = endOfWeek.format(formatter);
	    model.addAttribute("today", todayAsString);
	    model.addAttribute("tomorrow", tomorrowAsString);
	    model.addAttribute("toList", toList);
	    model.addAttribute("startDate", dateStart);
	    model.addAttribute("endDate", dateEnd);
	    
	    return "/todo/myTodo";
	}
	
	// 할 일 등록 페이지 
	@GetMapping("/insert.do")
	public String insertTodo(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);	
		return "/todo/insert";
	}
	
	// 캘린더에 할 일 목록 가져오기
	@ResponseBody
	@GetMapping("/selectTodo")
	public List<Todo> selectTodo(@AuthenticationPrincipal UserDetails userDetails) {
	    String userId = userDetails.getUsername();
	    List<Todo> tList = tService.selectTodoById(userId);
	    return tList;
	}
	
	// 할 일 등록 기능 
	@ResponseBody
	@PostMapping("/insert.do")
	public String insert(Todo todo
			, @RequestParam("title") String title
			, @RequestParam("start") Date start
			, @RequestParam("end") Date end
			, @AuthenticationPrincipal UserDetails userDetails) {
		
		String userId = userDetails.getUsername();
		Date startDate = start;
		Date endDate = end;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String FormatStart = format.format(startDate);
		String FormatEnd = format.format(endDate);
		System.out.println(FormatStart);
		System.out.println(FormatEnd);
		todo.setUserId(userId);
		todo.setTodoContent(title);
		todo.setStartDate(FormatStart);
		todo.setEndDate(FormatEnd);
		int result = tService.insertTodo(todo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 할 일 삭제 기능
	@ResponseBody
	@GetMapping("/delete.do")
	public String deleteTodo(@RequestParam("todoNo") Integer todoNo) {
		int result = tService.deleteTodoByNo(todoNo);
		if(result > 0) {
            return "success";
        } else {
            return "fail";
        }
	}
	// 할 일 체크 기능
	@ResponseBody
	@PostMapping("/updateStatus.do")
	public String updateStatus(@RequestParam Map<String, Object> paramMap) {
	    Integer result = tService.updateStatus(paramMap);
	    if(result > 0) {
	        return "success";
	    } else {
	        return "fail";
	    }
	}
	// 오늘 할 일 등록 기능(모달)
	@ResponseBody
	@PostMapping("/insertToday.do")
	public String insertToday(Todo todo
			, @RequestParam("todoContent") String todoContent
			, @RequestParam("startTime") String startTime
			, @RequestParam("endTime") String endTime
			, @AuthenticationPrincipal UserDetails userDetails) {
		
		String userId = userDetails.getUsername();
	    LocalDate currentDate = LocalDate.now();
	    LocalDateTime startDateTime = LocalDateTime.parse(currentDate + "T" + startTime);
	    LocalDateTime endDateTime = LocalDateTime.parse(currentDate + "T" + endTime);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String startDate = startDateTime.format(formatter);
	    String endDate = endDateTime.format(formatter);
	    todo.setUserId(userId);
	    todo.setStartDate(startDate);
	    todo.setEndDate(endDate);
	    todo.setTodoContent(todoContent);
	    Integer result = tService.insertTodo(todo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	// 내일 할 일 등록 기능(모달)
	@ResponseBody
	@PostMapping("/insertTomorrow.do")
	public String insertTomorrow(Todo todo
			, @RequestParam("todoContent") String tomorrowContent
			, @RequestParam("startTime") String tomorrowStartTime
			, @RequestParam("endTime") String tomorrowEndTime
			, @AuthenticationPrincipal UserDetails userDetails) {
		
		String userId = userDetails.getUsername();
	    LocalDate currentDate = LocalDate.now();
	    LocalDate tomorrow = currentDate.plusDays(1); // 내일의 날짜 구하기
	    LocalDateTime startDateTime = LocalDateTime.parse(tomorrow + "T" + tomorrowStartTime);
	    LocalDateTime endDateTime = LocalDateTime.parse(tomorrow + "T" + tomorrowEndTime);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String startDate = startDateTime.format(formatter);
	    String endDate = endDateTime.format(formatter);	
	    todo.setUserId(userId);
	    todo.setStartDate(startDate);
	    todo.setEndDate(endDate);
	    todo.setTodoContent(tomorrowContent);	    
	    Integer result = tService.insertTodo(todo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 모달 일정 상세 조회 기능
	@ResponseBody
	@PostMapping("/selectTodo.do")
	public Todo selectTodo(@RequestParam("todoNo") Integer todoNo
			, Model model) {
		Todo searchTodo = tService.selectTodo(todoNo);
		return searchTodo;
	}
	
	// 모달 일정 삭제 기능
	@ResponseBody
	@PostMapping("/deleteModal.do")
	public String deleteModal(@RequestParam("todoNo") Integer todoNo) {
		int result = tService.deleteTodoByNo(todoNo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	@ResponseBody
	@PostMapping("/update.do")
	public String updateTodo(Todo todo
	        , @RequestParam("todoNo") Integer todoNo
	        , @RequestParam("start") Date start
	        , @RequestParam("end") Date end) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formatStart = format.format(start);
	    String formatEnd = format.format(end);
	    System.out.println(formatStart);
	    System.out.println(formatEnd);
	    todo.setStartDate(formatStart);
	    todo.setEndDate(formatEnd);
	    int result = tService.modifyTodo(todo);
	    return "";
	}
}
