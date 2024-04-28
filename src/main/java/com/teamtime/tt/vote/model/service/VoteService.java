package com.teamtime.tt.vote.model.service;

import java.util.List;

import com.teamtime.tt.common.PageInfo;
import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.dto.VoteOption;
import com.teamtime.tt.vote.model.dto.VoteResult;
import com.teamtime.tt.vote.model.dto.needToDeleteVote;

public interface VoteService {

	/**
	 * 팀 번호로 투표 조회
	 * @param pInfo 
	 * @param teamNo
	 * @return List<Vote>
	 */
	List<Vote> selectVoteList(PageInfo pInfo, Integer teamNo);

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
	 * 투표한 항목 등록
	 * @param voteResult
	 * @return int
	 */
	int insertVoteResult(VoteResult voteResult);

	/**
	 * 투표에서 회원아이디로 회원이 투표한 정보 삭제
	 * @param voteNo
	 * @param userId
	 * @return int
	 */
	int deleteVoteResult(needToDeleteVote need);

	/**
	 * 투표 개수 조회
	 * @param teamNo 
	 * @return Integer
	 */
	Integer getTotalCount(Integer teamNo);

	/**
	 * 투표한 항목 전체 개수 
	 * @param voteNo
	 * @return Integer
	 */
	Integer getTotalVoteCount(Integer voteNo);

	/**
	 * 투표한 항목 단일 개수
	 * @param vo
	 * @return Integer
	 */
	Integer getOptionCount(VoteOption vo);

}
