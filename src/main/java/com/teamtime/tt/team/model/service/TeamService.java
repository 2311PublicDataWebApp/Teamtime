package com.teamtime.tt.team.model.service;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserTeam;

public interface TeamService {

	/**
	 * 팀 생성
	 * @param team
	 * @return Integer
	 */
	Integer insertTeam(Team team);

	/**
	 * 유저를 팀에 등록
	 * @param userTeam
	 * @return int
	 */
	int insertUserTeam(UserTeam userTeam);

}
