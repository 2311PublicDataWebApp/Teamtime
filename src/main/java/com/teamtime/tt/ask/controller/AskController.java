package com.teamtime.tt.ask.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.teamtime.tt.ask.model.dto.AskVO;
import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.ask.model.service.AskService;
import com.teamtime.tt.ask.model.service.ReplyService;
import com.teamtime.tt.common.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class AskController {
	
	@Autowired
	private AskService aService;
	@Autowired
	private ReplyService rService;
	@Value("${ask.editor.imglocation}")
	private String askFolder;

	public ModelAndView showRegisterForm(ModelAndView mv) {
		
		return mv;
	}
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 목록-----------------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/list.do", method=RequestMethod.GET)
	public String showAskList(Model model
			, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		try {
			Integer totalCount = 227;
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<AskVO> aList = aService.selectAskList(pInfo);
			if(!aList.isEmpty()) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("aList", aList);
			}else {
				// 없다고 알려줘야 함.
				// 1. 항상 에러페이지를 통해서 데이터가 없다고 했지만
				// 2. list.jsp에서 데이터가 존재하지 않습니다 메시지 출력하도록 할 수 있음
				model.addAttribute("aList", null);
			}
			return "ask/list";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 검색-----------------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value = "/ask/search.do", method = RequestMethod.GET)
    public String searchAskList(Model model,
            @RequestParam("searchCondition") String searchCondition,
            @RequestParam("searchKeyword") String searchKeyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("searchCondition", searchCondition);
            paramMap.put("searchKeyword", searchKeyword);
            int totalCount = aService.getTotalCount(paramMap);
            PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
            List<AskVO> searchList = aService.searchAskByKeyword(pInfo, paramMap);
            if (!searchList.isEmpty()) {
                model.addAttribute("aList", searchList);
                model.addAttribute("pInfo", pInfo);
                model.addAttribute("searchCondition", searchCondition);
                model.addAttribute("searchKeyword", searchKeyword);
            } else {
                model.addAttribute("aList", null); // 검색 결과가 없는 경우를 위해 추가된 부분
                model.addAttribute("pInfo", pInfo);
            }
            return "ask/list"; 
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "common/errorPage";
        }
    }
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 상세 조회-----------------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/detail.do", method=RequestMethod.GET)
	public String showAskDetail(Model model, Integer askNo) {
		try {
			AskVO askVO = aService.selectOneByNo(askNo);
//			List<ReplyVO> rList = rService.selectReplyList(askNo);
			model.addAttribute("ask", askVO);
//			model.addAttribute("rList", rList);
			return "ask/detail";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 페이징----------------------------------------------
	//------------------------------------------------------------------------------------------
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
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 등록 페이지 이동--------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/register.do", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "ask/register";
	}
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 등록 +첨부파일---------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/register.do", method=RequestMethod.POST)
	public String insertAsk(@ModelAttribute AskVO ask
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, Model model
			, HttpServletRequest request
			, HttpSession session) {
		try {
//			String writer = (String)session.getAttribute("userId");
			String writer = "kjw";
//			if(session != null && writer != null && !"".equals(writer)) {
//				ask.setAskWriter(writer);
//				if(uploadFile != null && !uploadFile.isEmpty()) {
//				 	Map<String, Object> aMap = this.saveFile(request, uploadFile);
//				 	ask.setAskFilename((String)bMap.get("fileName"));
//				 	ask.setAskFileRename((String)bMap.get("fileRename"));
//				 	ask.setAskFilepath((String)bMap.get("filePath"));
//				 	ask.setAskFilelength((long)bMap.get("fileLength"));
//				}
//			}else {
//				model.addAttribute("msg", "로그인이 필요합니다.");
//				return "common/errorPage";
//			}
			ask.setAskWriter(writer);
			int result = aService.insertAsk(ask);
			if(result > 0) {
				return "redirect:/ask/list.do";
			}else {
				model.addAttribute("msg", "공지사항 등록이 완료되지 않았습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
//		String askFolder = request.getSession().getServletContext().getRealPath("static");
		String savePath = askFolder + "\\auploadFiles";
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();		// 저장할 경로에 폴더가 없으면 폴더를 생성하도록 함.
		}
		String fileName = uploadFile.getOriginalFilename();
		// A : 1.png, B : 1.png 
		// 시간으로 파일 리네임하기
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + ext;
		File saveFile = new File(savePath+"\\"+fileRename);
		uploadFile.transferTo(saveFile); 	// 이 코드를 통해서 파일이 저장됨.
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/auploadFiles/"+fileRename);
		fileMap.put("fileLength", uploadFile.getSize());
		return fileMap;
	}
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 수정 페이지 이동--------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/update.do", method=RequestMethod.GET)
	public String showUpdateForm(Model model,@RequestParam("askNo") Integer askNo) {
	    try {
	        AskVO ask = aService.selectAskByNo(askNo);
	        if(ask != null) {
	            model.addAttribute("ask", ask);
	            return "ask/update";
	        } else {
	            model.addAttribute("msg", "데이터가 존재하지 않았습니다.");
	            return "common/errorPage";
	        }
	    } catch (Exception e) {
	        model.addAttribute("msg", e.getMessage());
	        return "common/errorPage";
	    }
	}

	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 수정-----------------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/update.do", method=RequestMethod.POST)
	public String updateAsk(
	        @ModelAttribute AskVO ask
	        , @RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
	        , HttpServletRequest request) {
	    try {
	        // 수정 기능 -> 1. 대체, 2. 삭제 후 등록
	        if(reloadFile != null && !reloadFile.isEmpty()) {
	            // 파일 처리 로직
	        }
	        int result = aService.updateAsk(ask);
	        if(result > 0) {
	            return "redirect:/ask/detail.do?askNo=" + ask.getAskNo();
	        } else {
	            // 실패 시 처리
	            return "common/errorPage";
	        }
	    } catch(Exception e) {
	        // 예외 발생 시 처리
	        return "common/errorPage";
	    }
	}
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 문의 삭제--------------------------------------
	//------------------------------------------------------------------------------------------
	@RequestMapping(value="/ask/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteAsk(ModelAndView mv, Integer askNo) {
		try {
			int result = aService.deleteAsk(askNo);
			if(result > 0) {
				mv.setViewName("redirect:/ask/list.do");
			}else {
				mv.addObject("msg", "데이터가 조회되지 않습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}		
	@PostMapping("/ckeditor/imageUpload.do")
	public String ckUploadImage(
			HttpServletRequest request
			, @RequestParam(value="upload") MultipartFile reloadFile) throws Exception {
		this.saveFile(request, reloadFile);
		return null;
	}
}
