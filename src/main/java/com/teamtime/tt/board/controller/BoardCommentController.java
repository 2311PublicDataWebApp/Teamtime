package com.teamtime.tt.board.controller;

import java.util.List;

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
			, Model model) {
		int result = bService.insertComment(comment);
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
	@GetMapping("delete.do")
	public String deleteComment(Integer commentNo) {
		int result = bService.deleteComment(commentNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
//	// 댓글 수정 기능
//	public String modifyComment(Integer commentNo) {
//		int result = bService.modiftComment(commentNo);
//		if(result > 0) {
//			return "success";
//		}else {
//			return "fail";
//		}
//	}
}
