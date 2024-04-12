package com.teamtime.tt.board.model.service.logic;

import org.springframework.stereotype.Service;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.mapper.BoardMapper;
import com.teamtime.tt.board.model.service.BoardService;

@Service
public class BoardServiceLogic implements BoardService{

	private BoardMapper bMapper;
	
	public BoardServiceLogic(BoardMapper bMapper) {
		this.bMapper = bMapper;
	}
	
	@Override
	public int insertBoard(Board board) {
		int result = bMapper.insertBoard(board);
		return result;
	}
}
