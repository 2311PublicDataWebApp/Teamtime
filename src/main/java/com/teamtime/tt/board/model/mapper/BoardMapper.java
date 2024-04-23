package com.teamtime.tt.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.dto.BoardComment;
import com.teamtime.tt.common.PageInfo;


@Mapper
public interface BoardMapper {
	/**
	 * 게시글 등록
	 * @param board
	 * @return int
	 */
	Integer insertBoard(Board board);

	/**
	 * 게시글 조회
	 * @return List
	 */
	List<Board> selectBoard(PageInfo pInfo, RowBounds rowBounds);

	/**
	 * 게시글 상세조회 
	 * @param boardNo
	 * @return Board
	 */
	Board selectBoardByNo(Integer boardNo);

	/**
	 * 댓글 등록
	 * @return int
	 */
	Integer insertComment(BoardComment comment);

	/**
	 * 댓글 조회
	 * @param boardNo
	 * @return List
	 */
	List<BoardComment> selectCommentList(Integer boardNo);

	/**
	 * 댓글 삭제
	 * @param boardNo
	 * @return int
	 */
	Integer deleteComment(Integer commentNo);

	/**
	 * 댓글 수정
	 * @param commentNo
	 * @return int
	 */
	Integer modifyComment(BoardComment comment);

	/**
	 * 페이징 게시물 전체 갯수
	 * @return
	 */
	Integer getTotalCount();

	/**
	 * 게시물 삭제 
	 * @param boardNo
	 * @return Integer
	 */
	Integer deleteBoard(Integer boardNo);

	/**
	 * 게시물 수정
	 * @param board
	 * @return Integer
	 */
	Integer modifyBoard(Board board);

	/**
	 * 검색 게시물 전체 갯수 
	 * @param paramMap
	 * @return int
	 */
	int getSearchTotalCount(Map<String, String> paramMap);

	/**
	 * 게시물 검색 
	 * @param rowBounds
	 * @param paramMap
	 * @return List
	 */
	List<Board> searchBoardByKeyword(RowBounds rowBounds, Map<String, String> paramMap);

}
