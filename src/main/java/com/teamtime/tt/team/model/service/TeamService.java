package com.teamtime.tt.team.model.service;

import java.util.List;

import com.teamtime.tt.team.model.dto.Team;

public interface TeamService {

	/**
	 * 아이디로 팀 조회
	 * @param userId
	 * @return List<Team>
	 */
	List<Team> selectTeamById(String userId);
	
	/**
	 * 팀 생성
	 * @param team
	 * @return Integer
	 */
	int insertTeam(Team team);

	/**
	 * 유저를 팀에 등록
	 * @param userIdOne
	 * @return int
	 */
	int insertUserTeam(String userId);

}
