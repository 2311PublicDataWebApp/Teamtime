package com.teamtime.tt.ask.model.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.ask.model.mapper.ReplyMapper;
import com.teamtime.tt.ask.model.service.ReplyService;

@Service
public class ReplyServiceLogic implements ReplyService{
	
	@Autowired
	private ReplyMapper rStore;
	
	@Override
	public int insertReply(ReplyVO replyVO) {
		int result = rStore.insertReply(replyVO);
		return result;
	}

	@Override
	public int updateReply(ReplyVO reply) {
		int result = rStore.updateReply(reply);
		return result;
	}

	@Override
	public int deleteReply(Integer replyNo) {
		int result = rStore.deleteReply(replyNo);
		return result;
	}

	@Override
	public List<ReplyVO> selectReplyList(Integer refAskNo) {
		List<ReplyVO> rList = rStore.selectReplyList(refAskNo);
		return rList;
	}

}
