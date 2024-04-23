package com.teamtime.tt.board.model.service.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.teamtime.tt.board.model.dto.Board;
import com.teamtime.tt.board.model.dto.BoardComment;
import com.teamtime.tt.board.model.mapper.BoardMapper;
import com.teamtime.tt.board.model.service.BoardService;
import com.teamtime.tt.common.PageInfo;

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
	public List<Board> selectBoard(PageInfo pInfo) {
		int limit = pInfo.getBoardLimit();
		int offSet = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offSet, limit);
		List<Board> bList = bMapper.selectBoard(pInfo, rowBounds);
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
	public int modifyComment(BoardComment comment) {
		int result = bMapper.modifyComment(comment);
		return result;
	}

	@Override
	public Integer getTotalCount() {
		Integer totalCount = bMapper.getTotalCount();
		return totalCount;
	}

	@Override
	public Integer deleteBoard(Integer boardNo) {
		Integer result = bMapper.deleteBoard(boardNo);
		return result;
	}

	@Override
	public Integer modifyBoard(Board board) {
		Integer result = bMapper.modifyBoard(board);
		return result;
	}

	@Override
	public int getSearchTotalCount(Map<String, String> paramMap) {
		int result = bMapper.getSearchTotalCount(paramMap);
		return result;
	}

	@Override
	public List<Board> searchBoardByKeyword(PageInfo pi, Map<String, String> paramMap) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Board> searchList = bMapper.searchBoardByKeyword(rowBounds, paramMap);
		return searchList;
	}
}
