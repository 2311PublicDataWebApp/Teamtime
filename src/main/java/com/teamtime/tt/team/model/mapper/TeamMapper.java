package com.teamtime.tt.team.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.team.model.dto.Team;

@Mapper
public interface TeamMapper {

	/**
	 * 아이디로 팀 조회
	 * @param userId
	 * @return List<team>
	 */
	List<Team> selectTeamById(String userId);

	/**
	 * 팀 생성
	 * @param team
	 * @return Team
	 */
	int insertTeam(Team team);

	/**
	 * 유저를 팀에 등록
	 * @param userId
	 * @return int
	 */
	int insertUserTeam(String userId);

}
