package com.teamtime.tt.board.model.service;

import java.util.List;
import java.util.Map;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.dto.BoardComment;
import com.teamtime.tt.common.PageInfo;

public interface BoardService {

	/**
	 * 게시글 등록
	 * @param board
	 * @return int
	 */
	int insertBoard(Board board);

	/**
	 * 게시글 조회
	 * @return List
	 */
	List<Board> selectBoard(PageInfo pInfo);

	/**
	 * 게시글 상세 조회
	 * @param boardNo
	 * @return Board
	 */
	Board selectBoardByNo(Integer boardNo);

	/**
	 * 댓글 등록
	 * @return
	 */
	int insertComment(BoardComment comment);

	/**
	 * 댓글 목록 조회
	 * @param boardNo
	 * @return List
	 */
	List<BoardComment> selectCommentList(Integer boardNo);

	/**
	 * 댓글 삭제 
	 * @param boardNo
	 * @return int
	 */
	int deleteComment(Integer commentNo);

	/**
	 * 댓글 수정
	 * @param comment
	 * @return int
	 */
	int modifyComment(BoardComment comment);

	/**
	 * 페이징 게시물 갯수
	 * @return Integer
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
	 * @return
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
	 * @param pi
	 * @param paramMap
	 * @return List
	 */
	List<Board> searchBoardByKeyword(PageInfo pi, Map<String, String> paramMap);
}
