package com.teamtime.tt.team.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserTeam;

@Mapper
public interface TeamMapper {

	/**
	 * 팀 생성
	 * @param team
	 * @return Team
	 */
	Team insertTeam(Team team);

	/**
	 * 유저를 팀에 등록
	 * @param userTeam
	 * @return int
	 */
	int insertUserTeam(UserTeam userTeam);

}
