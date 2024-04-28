package com.teamtime.tt.vote.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.common.PageInfo;
import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.dto.VoteOption;
import com.teamtime.tt.vote.model.dto.VoteResult;
import com.teamtime.tt.vote.model.dto.needToDeleteVote;
import com.teamtime.tt.vote.model.mapper.VoteMapper;
import com.teamtime.tt.vote.model.service.VoteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteServiceLogic implements VoteService {
	
	private final VoteMapper vMapper;

	@Override
	public List<Vote> selectVoteList(PageInfo pInfo, Integer teamNo) {
		List<Vote> vList = vMapper.selectVoteList(teamNo, pInfo.getRowBounds());
		return vList;
	}

	@Override
	public Vote selectVoteByNo(Integer voteNo) {
		Vote vote = vMapper.selectVoteByNo(voteNo);
		return vote;
	}

	@Override
	public List<VoteOption> selectVoteOptionList(Integer voteNo) {
		List<VoteOption> voList = vMapper.selectVoteOptionList(voteNo);
		return voList;
	}

	@Override
	public int insertVote(Vote vote) {
		int result = vMapper.insertVote(vote);
		return result;
	}

	@Override
	public int insertVoteOption(VoteOption vOption) {
		int result = vMapper.insertVoteOption(vOption);
		return result;
	}

	@Override
	public int insertVoteResult(VoteResult voteResult) {
		int result = vMapper.insertVoteResult(voteResult);
		return result;
	}

	@Override
	public int deleteVoteResult(needToDeleteVote need) {
		int result = vMapper.deleteVoteResult(need);
		return result;
	}

	@Override
	public Integer getTotalCount(Integer teamNo) {
		Integer totalCount = vMapper.getTotalCount(teamNo);
		return totalCount;
	}

	@Override
	public Integer getTotalVoteCount(Integer voteNo) {
		Integer totalCount = vMapper.getTotalVoteCount(voteNo);
		return totalCount;
	}

	@Override
	public Integer getOptionCount(VoteOption vo) {
		Integer optionCount = vMapper.getOptionCount(vo);
		return optionCount;
	}

}
