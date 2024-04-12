package com.teamtime.tt.board.model.service;

import com.teamtime.tt.board.model.dto.Board;

public interface BoardService {

	/**
	 * 게시글 등록
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);
}
