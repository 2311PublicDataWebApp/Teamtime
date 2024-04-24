package com.teamtime.tt.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {

	@GetMapping("/insert.do")
	public String showInsertTeam() {
		return "/team/insertTeam";
	}
}
