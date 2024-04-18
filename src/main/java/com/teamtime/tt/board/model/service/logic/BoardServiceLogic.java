package com.teamtime.tt.board.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.dto.BoardComment;
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

	@Override
	public List<Board> selectBoard() {
		List<Board> bList = bMapper.selectBoard();
		return bList;
	}

	@Override
	public Board selectBoardByNo(Integer boardNo) {
		Board board = bMapper.selectBoardByNo(boardNo);
		return board;
	}

	@Override
	public int insertComment(BoardComment comment) {
		int result = bMapper.insertComment(comment);
		return result;
	}

	@Override
	public List<BoardComment> selectCommentList(Integer boardNo) {
		List<BoardComment> bList= bMapper.selectCommentList(boardNo);
		return bList;
	}

	@Override
	public int deleteComment(Integer commentNo) {
		int result = bMapper.deleteComment(commentNo);
		return result;
	}

	@Override
	public int modiftComment(Integer commentNo) {
		int result = bMapper.modiftComment(commentNo);
		return result;
	}
}
