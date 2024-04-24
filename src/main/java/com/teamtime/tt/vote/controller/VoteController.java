package com.teamtime.tt.vote.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.service.VoteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
	
	private final VoteService vService;
	
	@GetMapping("/insert.do")
	public String showInsertVote() {
		return "/vote/insertVote";
	}
	
	@PostMapping("/insert.do")
	public String InsertVote(@AuthenticationPrincipal UserDetails userDetails
			, Model model
			, Vote vote
			, String[] voteOptions) {
		try {
			String userId = userDetails.getUsername();
			System.out.println("투표 값 확인 좌표");
		} catch (Exception e) {
			
		}
		return "";
	}

}
