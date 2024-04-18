package com.teamtime.tt.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService bService;
	
	public BoardController(BoardService bService) {
		this.bService = bService;
	}

	// 메인 페이지 이동
	@GetMapping("/main.do")
	public String showMainBoard(Model model) {
		List<Board> bList = bService.selectBoard();
		model.addAttribute("bList", bList);
		return "/board/main";
	}
	
	// 게시물 등록 페이지 이동
	@GetMapping("/insert.do")
	public String showInsertBoard(Model model) {
		return "/board/insert";
	}
	
	// 게시물 등록
	@PostMapping("/insertBoard.do")
	public String insertBoard(@ModelAttribute Board board) {
		int result = bService.insertBoard(board);
		return "redirect:/board/main.do";
	}
	
	// 게시물 상세조회
	@GetMapping("/detail.do")
	public String showBoardDetail(Integer boardNo, Model model) {
		Board board = bService.selectBoardByNo(boardNo);
		model.addAttribute("board", board);
		return "/board/boardDetail";
	}
}
