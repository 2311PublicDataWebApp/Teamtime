package com.teamtime.tt.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@GetMapping("/myTodo")
	public String showMyTodo() {
		return "/todo/myTodo";
	}
	
	@GetMapping("insert")
	public String insertTodo() {
		return "/todo/insert";
	}
}
