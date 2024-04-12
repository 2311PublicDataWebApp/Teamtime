package com.teamtime.tt.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService bService;
	
	public BoardController(BoardService bService) {
		this.bService = bService;
	}

	@GetMapping("/main.do")
	public String showMainBoard() {
		return "/board/main";
	}
	
	@GetMapping("/insert.do")
	public String showInsertBoard(Model model) {
//		List<Board> bList = bService.selectBoard();
		return "/board/insert";
	}
	
	@PostMapping("/insertBoard.do")
	public String insertBoard(@ModelAttribute Board board) {
		int result = bService.insertBoard(board);
		return "";
	}
}
