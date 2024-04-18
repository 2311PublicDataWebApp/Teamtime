package com.teamtime.tt.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.board.model.dto.BoardComment;
import com.teamtime.tt.board.model.service.BoardService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/board/comment")
public class BoardCommentController {
	
    private BoardService bService;
    
	public BoardCommentController(BoardService bService) {
		this.bService = bService;
	}

	// 댓글 등록 기능
	@ResponseBody
	@PostMapping("/insert.do")
	public String insertComment(@ModelAttribute BoardComment comment
			, @AuthenticationPrincipal UserDetails userDetails
			, Model model) {
		String userId = userDetails.getUsername();
		System.out.println(userId);
		int result = 0;
		if(userId != null && !userId.equals("")) {
			comment.setUserId(userId);
			result = bService.insertComment(comment);
		}else {
			return "login needed";
		}
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 댓글 목록 조회 기능
	@ResponseBody
	@GetMapping("/list.do")
	public List<BoardComment> showCommentList(@RequestParam("boardNo") Integer boardNo) {
		List<BoardComment> bList = bService.selectCommentList(boardNo);
    	return bList;
	}
	
	// 댓글 삭제 기능
	@ResponseBody
	@GetMapping("/delete.do")
	public String deleteComment(Integer commentNo) {
		int result = bService.deleteComment(commentNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 댓글 수정 기능
	@ResponseBody
	@PostMapping("/update.do")
	public String modifyComment(BoardComment comment) {
		int result = bService.modifyComment(comment);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
