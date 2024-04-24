package com.teamtime.tt.ask.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.teamtime.tt.ask.model.dto.AskFileVO;
import com.teamtime.tt.ask.model.dto.AskVO;
import com.teamtime.tt.ask.model.dto.ReplyVO;
import com.teamtime.tt.common.PageInfo;

@Mapper
public interface AskMapper {
	/**
	 * 1대1 문의 등록 store
	 * @param ask
	 * @return
	 */
	public int insertAsk(AskVO ask);

	
	/**
	 * 1대1 문의 목록 store	
	 * @param session
	 * @param rowbounds
	 * @return
	 */
	public List<AskVO> selectAskList(RowBounds rowbounds);

	/**
	 * 1대1 문의 상세 store
	 * @param askNo
	 * @return
	 */
	public AskVO selectAskByNo(Integer askNo);

	/**
	 * 1대1 문의 상세 조회 Store
	 * @param askNo
	 * @return AskVO
	 */
//	public AskVO selectOneByNo(Integer askNo);

	/**
	 * 1대1 문의 수정 Store
	 * @param ask
	 * @return
	 */
	public int updateAsk(AskVO ask);

	/**
	 * 1대1 문의 삭제 Store
	 * @param askNo
	 * @return
	 */
	public int deleteAsk(int askNo);

	
	/**
	 * 1대1 문의 게시판 검색 게시물 전체 갯수 Store
	 * @param paramMap
	 * @return
	 */
	public int selectTotalCount(Map<String, String> paramMap);


	/**
	 * 1대1 문의 게시판 검색 Store
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	public List<AskVO> selectAskByKeyword(Map<String, String> paramMap, RowBounds rowBounds);
	
	/**
	 * 1대1 문의 게시판 답변 Store
	 * @param replyVO
	 * @return
	 */
	int insertReply(ReplyVO replyVO);

	/**
	 * 1대1 문의 게시판 답변 수정 Store
	 * @param replyVO
	 * @return
	 */
	int updateReply(ReplyVO reply);

	/**
	 * 1대1 문의 게시판 답변 삭제 Store
	 * @param replyVO
	 * @return
	 */
	int deleteReply(Integer replyNo);

	/**
	 * 1대1 문의 게시판 답변 목록 Store
	 * @param replyVO
	 * @return
	 */
	List<ReplyVO> selectReplyList(Integer refAskNo);

	/**
	 * 1대1 문의 게시판 첨부 파일 Store
	 * @param replyVO
	 * @return
	 */
	public int insertAskFile(AskFileVO askFile);


	/** 1대1 문의 게시판 첨부 파일 보여주기 Store
	 * 
	 * @param askNo
	 * @return
	 */
	public List<AskFileVO> selectAskFilesByAskNo(Integer askNo);

}
