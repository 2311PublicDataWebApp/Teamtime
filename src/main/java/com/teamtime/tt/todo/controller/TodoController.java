package com.teamtime.tt.todo.controller;




import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.teamtime.tt.todo.model.dto.Todo;
import com.teamtime.tt.todo.model.service.TodoService;


@Controller
@RequestMapping("/todo")
public class TodoController {
	
	private TodoService tService;
	
	public TodoController(TodoService tService) {
		this.tService = tService;
	}
	
	// 나의투두 페이지 이동
	@GetMapping("/myTodo.do")
	public String showMyTodo(@AuthenticationPrincipal UserDetails userDetails
			, Model model) {
		String userId = userDetails.getUsername();
		List<Todo> tList = tService.selectTodoById(userId);
//		for(int i = 0; i < tList.size(); i++) {
//			String startYear = tList.get(i).getStartDate().substring(0,10);
//			String endYear = tList.get(i).getEndDate().substring(0,10);
//			tList.get(i).setStartDate(startYear);
//			tList.get(i).setEndDate(endYear);
//		}
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayAsString = today.format(formatter);
		LocalDate tomorrow = today.plusDays(1);
		String tomorrowAsString = tomorrow.format(formatter);
		model.addAttribute("today", todayAsString);
		model.addAttribute("tomorrow", tomorrowAsString);
		model.addAttribute("tList", tList);
		return "/todo/myTodo";
	}
	
	// 할 일 등록 페이지 
	@GetMapping("/insert.do")
	public String insertTodo() {
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
}
