package com.teamtime.tt.ask.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.teamtime.tt.ask.model.dto.AskVO;
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
	 * @param pInfo
	 * @return
	 */
	public List<AskVO> selectAskList(PageInfo pInfo);

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
	
}
