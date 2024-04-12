package com.teamtime.tt.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.board.model.dto.Board;


@Mapper
public interface BoardMapper {
	/**
	 * 게시글 등록
	 * @param board
	 * @return int
	 */
	int insertBoard(Board board);
}
