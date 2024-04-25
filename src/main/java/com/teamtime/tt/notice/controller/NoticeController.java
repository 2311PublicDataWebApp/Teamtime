package com.teamtime.tt.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.notice.model.dto.Notice;
import com.teamtime.tt.notice.model.dto.NoticePageInfo;
import com.teamtime.tt.notice.model.service.NoticeService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService nService;
	@Autowired
	private AlarmService aService;
	@Autowired
	private UserService uService;

	// 공지 등록 insert
	@GetMapping("/notice/insert.do")
	public String showInsertForm(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
	    // 세션 로그인 확인
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		return "notice/insert";
	}
	
	@PostMapping("/notice/insert.do")
	public ModelAndView insertNotice(ModelAndView mv,
			@AuthenticationPrincipal UserDetails userDetails,
			@ModelAttribute Notice notice,
			HttpServletRequest request
			) {
		try {
			// 공지정보 저장
			String senderId = userDetails.getUsername();
			int result = nService.insertNotice(notice);
			Alarm alarm = new Alarm(senderId, "NOTICE","[공지 등록] 새로운 서버 공지가 등록었습니다.");
			int result2 = aService.insertAlarm(alarm);
			if(result > 0) {
				mv.setViewName("redirect:/notice/list.do");
			}else {
				mv.addObject("msg", "공지 등록이 완료되지 않음");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지 목록 list
	@GetMapping("/notice/list.do")
	public ModelAndView NoticeList(ModelAndView mv,
			@RequestParam(value="page", 
            required=false, defaultValue="1") Integer currentPage
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model) {
		try {
			int totalCount = nService.getTotalCount();
		    // 세션 로그인 확인
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			NoticePageInfo pi = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = nService.selectNoticeList(pi);
			mv.addObject("nList", nList);
			mv.addObject("pi", pi);
			mv.setViewName("notice/list");
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지 상세 detail
	@GetMapping("/notice/detail.do")
	public ModelAndView NoticeDetail(ModelAndView mv, int noticeNo) {
		try {
			Notice notice = nService.selectByNoticeNo(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice);
				mv.setViewName("notice/detail");
			}else {
				mv.addObject("msg", "데이터 존재안함");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	

	// 공지 수정 페이지이동
	@GetMapping("/notice/modify.do")
	public ModelAndView showModifyForm(ModelAndView mv, int noticeNo) {
		try {
			Notice notice = nService.selectByNoticeNo(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice);
				mv.setViewName("notice/modify");
			}else {
				mv.addObject("msg", "데이터가 없습니다");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지 수정
	@PostMapping("/notice/modify.do")
	public ModelAndView noticeUpdate(ModelAndView mv, @ModelAttribute Notice notice,
			@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile, HttpServletRequest request,
			int noticeNo) {
		try {
			if (reloadFile != null && !reloadFile.isEmpty()) {
				String fileName = notice.getNoticeFileRename();
				if (fileName != null) {
					// 있으면 파일 삭제
					this.deleteFile(request, fileName);
				}
				// 없으면 새로 업로드하려는 파일 저장
				Map<String, Object> infoMap = this.saveFile(reloadFile, request);
				String noticeFilename = (String) infoMap.get("fileName");
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename((String) infoMap.get("fileRename"));
			}
			int result = nService.updateNotice(notice);
			if (result > 0) {
				mv.setViewName("redirect:/notice/detail.do?noticeNo=" + notice.getNoticeNo());
			} else {
				mv.addObject("msg", "데이터가 존재하지 않습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지 삭제
	@GetMapping("/notice/delete.do")
	public ModelAndView noticeDelete(ModelAndView mv, int noticeNo) {
		try {
			int result = nService.noticeDelete(noticeNo);
			if (result > 0) {
				mv.setViewName("redirect:/notice/list.do");
			}else {
				mv.addObject("msg","데이터가 존재하지않습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 공지사항 검색
	@GetMapping("/notice/search.do")
	public ModelAndView searchNoticeList(ModelAndView mv
 			, @RequestParam("searchCondition") String searchCondition
 			, @RequestParam("searchKeyword") String searchKeyword
 			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
 		
 		Map<String, String> paramMap = new HashMap<String, String>();
 		paramMap.put("searchCondition", searchCondition);
 		paramMap.put("searchKeyword", searchKeyword);
 		int totalCount = nService.getTotalCount(paramMap);
 		NoticePageInfo pi = this.getPageInfo(currentPage, totalCount);
 		List<Notice> searchList = nService.searchNoticeByKeyword(pi, paramMap);
 		mv.addObject("sList", searchList);
 		mv.addObject("pi", pi);
 		mv.addObject("searchCondition", searchCondition);
 		mv.addObject("searchKeyword", searchKeyword);
 		mv.setViewName("notice/search");
 		return mv;
 	}
	
	// 페이징
	private NoticePageInfo getPageInfo(Integer currentPage, int totalCount) {
		NoticePageInfo pi = null;
		int boardLimit = 10; // 한 페이지당 보여줄 게시물의 갯수
		int naviLimit = 5; 	 // 한 페이지당 보여줄 범위의 갯수
		int naviTotalCount; 		 // 범위의 총 갯수
		int startNavi;
		int endNavi;
		
		naviTotalCount = (int)((double) totalCount / boardLimit + 0.9);
		startNavi = (((int)((double) currentPage / naviLimit + 0.9)) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pi = new NoticePageInfo(currentPage, totalCount, naviTotalCount, boardLimit, naviLimit, startNavi,
				endNavi);
		return pi;
	}

	
	// 파일삭제
	private void deleteFile(HttpServletRequest request, String fileName) {
		String rPath = request.getSession().getServletContext().getRealPath("resources");
		String delFilePath = rPath + "\\nuploadFiles\\" + fileName;
		File delFile = new File(delFilePath);
		if (delFile.exists()) {
			delFile.delete();
		}
	}
	
	// 파일저장
	private Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws IllegalStateException, IOException {
		String fileName = uploadFile.getOriginalFilename();
		// 저장 경로
		String projectPath = request.getSession().getServletContext().getRealPath("resources");
		String saveDirectory = projectPath + "\\nuploadFiles";
		File sDir = new File(saveDirectory);
		if(!sDir.exists()) {
			sDir.mkdir(); 
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 나중에 SS랑 비교
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		String ext = fileName.substring(fileName.lastIndexOf(".")+1); // 원본파일의 확장자 글자부터 시작되도록 +1 해줌
		String fileRename = strResult + "." + ext;
		
		String savePath = saveDirectory + "\\" + fileRename;
		File file = new File(savePath);
		// 파일 저장
		uploadFile.transferTo(file);
		// DB에 저장할 파일정보 셋팅
		// 1. 파일이름, 2.파일 리네임, 3. 파일경로, 4.파일크기
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		return infoMap;
	}
	
}
