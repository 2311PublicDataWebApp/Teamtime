package com.teamtime.tt.team.model.service.logic;

import org.springframework.stereotype.Service;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserTeam;
import com.teamtime.tt.team.model.mapper.TeamMapper;
import com.teamtime.tt.team.model.service.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceLogic implements TeamService {

	private final TeamMapper tMapper;
	
	@Override
	public Integer insertTeam(Team team) {
		Team tOne = tMapper.insertTeam(team);
		Integer teamNo = tOne.getTeamNo();
		return teamNo;
	}

	@Override
	public int insertUserTeam(UserTeam userTeam) {
		int result = tMapper.insertUserTeam(userTeam);
		return result;
	}

}
