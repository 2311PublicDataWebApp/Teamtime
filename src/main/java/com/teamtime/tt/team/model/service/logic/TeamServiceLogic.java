package com.teamtime.tt.team.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;
import com.teamtime.tt.team.model.mapper.TeamMapper;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceLogic implements TeamService {

	private final TeamMapper tMapper;

	@Override
	public Team selectTeamByNo(Integer teamNo) {
		Team team = tMapper.selectTeamByNo(teamNo);
		return team;
	}

	@Override
	public List<UserJoinTeam> selectTeamById(String userId) {
		List<UserJoinTeam> tList = tMapper.selectTeamById(userId);
		return tList;
	}

	@Override
	public List<User> selectUsersInTeam(Integer teamNo) {
		List<User> uList = tMapper.selectUsersInTeam(teamNo);
		return uList;
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

	@Override
	public int insertUserTeam(UserJoinTeam ujt) {
		int result = tMapper.insertUserTeamByNo(ujt);
		return result;
	}

	@Override
	public int updateTeam(Team team) {
		int result = tMapper.updateTeam(team);
		return result;
	}

	@Override
	public int deleteUserInTeam(UserTeam ut) {
		int result = tMapper.deleteUserInTeam(ut);
		return result;
	}

	@Override
	public int deleteUsersInTeam(Integer teamNo) {
		int result = tMapper.deleteUsersInTeam(teamNo);
		return result;
	}

}
