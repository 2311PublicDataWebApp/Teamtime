package com.teamtime.tt.ask.model.service.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtime.tt.ask.model.dto.AskVO;
import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.ask.model.mapper.AskMapper;
import com.teamtime.tt.ask.model.service.AskService;
import com.teamtime.tt.common.PageInfo;

@Service
public class AskServiceLogic implements AskService{

	@Autowired
	private AskMapper aStore;
	
	
	@Override
	public List<AskVO> selectAskList(PageInfo pInfo) {
		List<AskVO> aList = aStore.selectAskList(pInfo);
		return aList;
	}

	//1대1 문의하기 상세조회
	@Override
	public AskVO selectOneByNo(Integer askNo) {
		AskVO askVO = aStore.selectAskByNo(askNo);
		return askVO;
	}

	//1대1 문의하기 삭제
	@Override
	public int deleteAsk(int askNo) {
		int result = aStore.deleteAsk(askNo);
		return result;
	}

	
	// 1대1 문의하기 등록
	@Override
	public int insertAsk(AskVO ask) {
		int result= aStore.insertAsk(ask);
		return result;
	}

	//1대1 문의하기 수정 페이지 이동
	@Override
	public AskVO selectAskByNo(Integer askNo) {
		AskVO ask = aStore.selectAskByNo(askNo);
		return ask;
	}
	//1대1 문의하기 수정
	@Override
	public int updateAsk(AskVO ask) {
		int result = aStore.updateAsk(ask);
		return result;
	}

	//1대1 문의하기 검색
	@Override
	public int getTotalCount(Map<String, String> paramMap) {
		int totalCount = aStore.selectTotalCount(paramMap);
		return totalCount;
	}

	//1대1 문의하기
	@Override
	public List<AskVO> searchAskByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getBoardLimit();
		int offSet = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offSet, limit);
		List<AskVO> searchList = aStore.selectAskByKeyword(paramMap, rowBounds);
		return searchList;
	}
	
	@Override
	public int insertReply(ReplyVO replyVO) {
		int result = aStore.insertReply(replyVO);
		return result;
	}

	@Override
	public int updateReply(ReplyVO reply) {
		int result = aStore.updateReply(reply);
		return result;
	}

	@Override
	public int deleteReply(Integer replyNo) {
		int result = aStore.deleteReply(replyNo);
		return result;
	}

	@Override
	public List<ReplyVO> selectReplyList(Integer refAskNo) {
		List<ReplyVO> rList = aStore.selectReplyList(refAskNo);
		return rList;
	}

}
