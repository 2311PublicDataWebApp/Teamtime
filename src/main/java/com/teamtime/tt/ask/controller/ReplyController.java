package com.teamtime.tt.ask.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.ask.model.service.ReplyService;

import jakarta.servlet.http.HttpSession;





@Controller
public class ReplyController {
	@Autowired
	private ReplyService rService;
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 답변 등록-------------------------------------------
	//------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/reply/add.do", method=RequestMethod.POST)
	public String insertReplyAjax(@ModelAttribute ReplyVO reply
			, HttpSession session) {
		try {
//			String replyWriter = (String)session.getAttribute("userId");
			String replyWriter = "kjw";
			int result = 0;
			if(replyWriter != null && !replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				result = rService.insertReply(reply);
			}else {
				return "Login needed";
			}
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 답변 수정-------------------------------------------
	//------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/reply/update.do", method=RequestMethod.POST)
	public String updateReply(@ModelAttribute ReplyVO reply) {
		// UPDATE REPLY_TBL SET REPLY_CONTENT = #{replyContent}
		// , UPDATE_DATE = DEFAULT
		// WHERE REPLY_NO = #{replyNo }
		try {
			int result = rService.updateReply(reply);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 답변 삭제-------------------------------------------
	//------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/reply/delete.do", method=RequestMethod.POST)
	public String deleteReply(Integer replyNo) {
		try {
			int result = rService.deleteReply(replyNo);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//------------------------------------------------------------------------------------------
	//-----------------------------1:1 문의하기 답변 목록-------------------------------------------
	//------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/reply/list.do"
			, method=RequestMethod.GET)
	public List<ReplyVO> showReplyList(@RequestParam("refAskNo") Integer refAskNo) throws IOException {
		// DB에서 댓글목록 가져오기
		List<ReplyVO> rList = rService.selectReplyList(refAskNo);
		// ReplyVO -> JSON 변환시 json-simple 라이브러리 필요
		// List -> JSON Array로 만들어서 리턴해줘야 함
//		JSONObject json = new JSONObject();
//		JSONArray jsonArr = new JSONArray();
//		for(ReplyVO reply : rList) {
//			json.put("replyNo"		, reply.getReplyNo());
//			json.put("refAskNo"	, reply.getrefAskNo());
//			json.put("replyContent" , reply.getReplyContent());
//			json.put("replyWriter"	, reply.getReplyWriter());
//			json.put("rCreateDate"	, reply.getrCreateDate());
//			json.put("rUpdateDate"	, reply.getrUpdateDate());
//			json.put("updateYn"		, reply.getUpdateYn());
//			json.put("rStatus"		, reply.getrStatus());
//			// { "replyNo" : 1, "refAskNo" : 222, "replyContent" : "댓글내용", ... }
//			jsonArr.add(json);
//			// -> [ {}, {}, {}, .... ]
//		}
		// List -> JSON Array로 간단히 바꿔주는 라이브러리 2번째
		// GSON - Google JSON
		return rList;
		// 라이브러리 3번째
//		ObjectMapper mapper = new ObjectMapper();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        
//		mapper.setDateFormat(dateFormat);
//		return mapper.writeValueAsString(rList);
	}
	
	
//	@RequestMapping(value="/reply/add.do", method=RequestMethod.POST)
//	public String insertReply(Model model
//			, HttpSession session
//			, @ModelAttribute ReplyVO replyVO) {
//		try {
//			String memberId = (String)session.getAttribute("memberId");
//			if(memberId != null) {
//				replyVO.setReplyWriter(memberId);
//			}else {
//				model.addAttribute("msg", "로그인이 필요합니다.");
//				return "common/errorPage";
//			}
//			int result = rService.insertReply(replyVO);
//			return "redirect:/board/detail.do?boardNo="+replyVO.getrefAskNo();
//		} catch (Exception e) {
//			model.addAttribute("msg", e.getMessage());
//			return "common/errorPage";
//		}
//	}
}
