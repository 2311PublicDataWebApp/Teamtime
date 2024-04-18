package com.teamtime.tt.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.dto.BoardComment;


@Mapper
public interface BoardMapper {
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
	List<Board> selectBoard();

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
	int insertComment(BoardComment comment);

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
	int deleteComment(Integer commentNo);

	/**
	 * 댓글 수정
	 * @param commentNo
	 * @return int
	 */
	int modiftComment(Integer commentNo);

}
