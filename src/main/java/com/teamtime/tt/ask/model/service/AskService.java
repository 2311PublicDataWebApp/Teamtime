package com.teamtime.tt.ask.model.service;

import java.util.List;
import java.util.Map;

import com.teamtime.tt.ask.model.dto.AskVO;
import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.common.PageInfo;

public interface AskService {
	/**
	 * 1대1 문의 등록 
	 * @param ask
	 * @return
	 */
	int insertAsk(AskVO ask);

	/**
	 * 1대1 문의 목록 조회 Service
	 * @param pInfo
	 * @return List
	 */
	List<AskVO> selectAskList(PageInfo pInfo);

	/**
	 * 1대1 문의 상세 조회 Service 
	 * @param askNo
	 * @return ArsVO
	 */
	AskVO selectOneByNo(Integer askNo);

	/**
	 * 1대1 문의 삭제 Service
	 * @param askNo
	 * @return
	 */
	int deleteAsk(int askNo);

	
	/**
	 * 1대1 문의 상세 Service
	 * @param askNo
	 * @return
	 */
	AskVO selectAskByNo(Integer askNo);

	
	/**
	 * 1대1 문의 수정 Service
	 * @param ask
	 * @return
	 */
	int updateAsk(AskVO ask);

	
	/**
	 * 1대1 문의 게시물 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getTotalCount(Map<String, String> paramMap);

	/**
	 * 1대1 문의 검색 Service
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<AskVO> searchAskByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 댓글 등록 Service
	 * @param replyVO
	 * @return int
	 */
	int insertReply(ReplyVO replyVO);

	/**
	 * 댓글 수정 Service
	 * @param reply
	 * @return int
	 */
	int updateReply(ReplyVO reply);

	/**
	 * 댓글 삭제 Service
	 * @param replyNo
	 * @return int
	 */
	int deleteReply(Integer replyNo);

	/**
	 * 댓글 목록 조회 Service
	 * @return List
	 */
	List<ReplyVO> selectReplyList(Integer refAskNo);

}
