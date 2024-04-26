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

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.service.BoardService;
import com.teamtime.tt.common.PageInfo;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService bService;
	private final UserService uService;
	private final AlarmService aService;
	private final TeamService tService;
	
	// 메인 페이지 이동
	@GetMapping("/main.do")
	public String showMainBoard(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, Board board) {
		Integer totalCount = bService.getTotalCount();
		int boardLimit = 17;
		PageInfo pInfo = new PageInfo(currentPage, totalCount, boardLimit);
		List<Board> bList = bService.selectBoard(pInfo);
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		if(!bList.isEmpty()) {
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("bList", bList);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
		}else {
			
		}
		return "/board/list";
	}
	
	// 게시물 등록 페이지 이동
	@GetMapping("/insert.do")
	public String showInsertBoard(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);	
		return "/board/insert";
	}
	
	// 게시물 등록
	@PostMapping("/insertBoard.do")
	public String insertBoard(@ModelAttribute Board board
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
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
	public String showBoardDetail(Integer boardNo
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		String writer = userDetails.getUsername();
	    User user = uService.selectUserById(writer);
	    List<Alarm> aList = aService.selectUnreadAlarm(writer);
	    model.addAttribute("user", user);
		session.setAttribute("aList", aList);		
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
	public String showModifyBoard(Integer boardNo
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
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
	
//	// 게시물 검색 기능
//	@ResponseBody
//	@GetMapping("/search.do")
//	public List<Board> searchBoard(@RequestParam("searchCondition") String searchCondition
//			, @RequestParam("searchContent") String searchContent
//			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
//		
//		Map<String, String> paramMap = new HashMap<String, String>();
// 		paramMap.put("searchCondition", searchCondition);
// 		paramMap.put("searchContent", searchContent);
// 		int totalCount = bService.getSearchTotalCount(paramMap);
// 		PageInfo pi = this.getPageInfo(currentPage, totalCount);
// 		List<Board> searchList = bService.searchBoardByKeyword(pi, paramMap);
// 		System.out.println(searchList);
//		return searchList;
//	}
}
