package com.teamtime.tt.ask.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.teamtime.tt.ask.model.dto.ReplyVO;

@Mapper
public interface ReplyMapper {

	
	int insertReply(ReplyVO replyVO);

	int updateReply(ReplyVO reply);

	int deleteReply(Integer replyNo);

	List<ReplyVO> selectReplyList(Integer refAskNo);

}
