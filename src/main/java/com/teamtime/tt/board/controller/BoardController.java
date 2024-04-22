package com.teamtime.tt.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.ask.model.dto.AskVO;
import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.service.BoardService;
import com.teamtime.tt.common.PageInfo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService bService;
	
	public BoardController(BoardService bService) {
		this.bService = bService;
	}

	// 메인 페이지 이동
	@GetMapping("/main.do")
	public String showMainBoard(Model model
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		Integer totalCount = bService.getTotalCount();
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Board> bList = bService.selectBoard(pInfo);
		if(!bList.isEmpty()) {
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("bList", bList);
		}else {
			model.addAttribute("aList", null);
		}
		return "/board/main";
	}
	
	// 페이징 처리
	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		PageInfo pInfo = new PageInfo();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		int naviTotalCount;
		int startNavi;
		int endNavi;
		// Math.ceil()을 이용해서 올림하고 int 강제형변환으로 정수가 나오도록 함
		naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pInfo.setCurrentPage(currentPage);
		pInfo.setBoardLimit(10);
		pInfo.setNaviLimit(5);
		pInfo.setTotalCount(totalCount);
		pInfo.setNaviTotalCount(naviTotalCount);
		pInfo.setStartNavi(startNavi);
		pInfo.setEndNavi(endNavi);
		return pInfo;
	}
	
	// 게시물 등록 페이지 이동
	@GetMapping("/insert.do")
	public String showInsertBoard(Model model) {
		return "/board/insert";
	}
	
	// 게시물 등록
	@PostMapping("/insertBoard.do")
	public String insertBoard(@ModelAttribute Board board
			, @AuthenticationPrincipal UserDetails userDetails
			) {
		String userId = userDetails.getUsername();
		System.out.println(userId);
		int result = 0;
		if(userId != null && !userId.equals("")) {
			board.setUserId(userId);
			result = bService.insertBoard(board);
		}else {
			return "login needed";
		}
		return "redirect:/board/main.do";
	}
	
	// 게시물 상세 조회
	@GetMapping("/detail.do")
	public String showBoardDetail(Integer boardNo, Model model
			, @AuthenticationPrincipal UserDetails userDetails
			) {
		String writer = userDetails.getUsername();
		Board board = bService.selectBoardByNo(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("writer", writer);
		return "/board/boardDetail";
	}
	
	// 게시물 삭제 기능
	@ResponseBody
	@GetMapping("/delete.do")
	public String deleteBoard(Integer boardNo) {
		Integer result = bService.deleteBoard(boardNo);
		if(result > 0) {
			return "success";			
		}else {
			return "fail";
		}
	}
	
	// 게시물 수정 페이지 이동
	@GetMapping("/modify.do")
	public String showModifyBoard(Model model, Integer boardNo) {
		Board board = bService.selectBoardByNo(boardNo);
		model.addAttribute("board", board);
		return "/board/modify";
	}
	
	// 게시물 수정 기능
	@PostMapping("/modify.do")
	public String modifyBoard(Board board) {
		Integer result = bService.modifyBoard(board);
		return "redirect:/board/detail.do?boardNo=" + board.getBoardNo();
	}
	
	// 게시물 검색 기능
	@ResponseBody
	@GetMapping("/search.do")
	public List<Board> searchBoard(@RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchContent") String searchContent
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
 		paramMap.put("searchCondition", searchCondition);
 		paramMap.put("searchContent", searchContent);
 		int totalCount = bService.getSearchTotalCount(paramMap);
 		PageInfo pi = this.getPageInfo(currentPage, totalCount);
 		List<Board> searchList = bService.searchBoardByKeyword(pi, paramMap);
 		System.out.println(searchList);
		return searchList;
	}
}
