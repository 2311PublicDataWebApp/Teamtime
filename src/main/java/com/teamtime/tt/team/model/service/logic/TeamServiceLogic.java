package com.teamtime.tt.team.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.mapper.TeamMapper;
import com.teamtime.tt.team.model.service.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceLogic implements TeamService {

	private final TeamMapper tMapper;

	@Override
	public List<Team> selectTeamById(String userId) {
		List<Team> tList = tMapper.selectTeamById(userId);
		return tList;
	}

	@Override
	public int insertTeam(Team team) {
		int result = tMapper.insertTeam(team);
		return result;
	}

	@Override
	public int insertUserTeam(String userId) {
		int result = tMapper.insertUserTeam(userId);
		return result;
	}

}
