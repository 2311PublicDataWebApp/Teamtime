package com.teamtime.tt.vote.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.dto.VoteOption;

@Mapper
public interface VoteMapper {

	/**
	 * 투표 목록 조회
	 * @param rowBounds
	 * @param teamNo
	 * @return List<Vote>
	 */
	List<Vote> selectVoteList(Integer teamNo, RowBounds rowBounds);

	/**
	 * 투표 번호로 투표 조회
	 * @param voteNo
	 * @return Vote
	 */
	Vote selectVoteByNo(Integer voteNo);

	/**
	 * 투표 번호로 항목 리스트 조회
	 * @param voteNo
	 * @return List<VoteOption>
	 */
	List<VoteOption> selectVoteOptionList(Integer voteNo);

	/**
	 * 투표 등록
	 * @param vote
	 * @return int
	 */
	int insertVote(Vote vote);

	/**
	 * 투표 항목 입력
	 * @param voteOption
	 * @return int
	 */
	int insertVoteOption(VoteOption vOption);

	/**
	 * 투표 개수 조회
	 * @param teamNo 
	 * @return
	 */
	Integer getTotalCount(Integer teamNo);

}
