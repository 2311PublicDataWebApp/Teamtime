package com.teamtime.tt.calender.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.calender.model.dto.Calender;
import com.teamtime.tt.calender.model.mapper.CalenderMapper;
import com.teamtime.tt.calender.model.service.CalenderService;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalenderServiceLogic implements CalenderService{
	
	private final CalenderMapper cMapper;

	@Override
	public int insertCalender(Calender calender) {
		int result = cMapper.insertCalender(calender);
		return result;
	}

	@Override
	public int deleteByNo(Integer calenderNo) {
		int result = cMapper.deleteByNo(calenderNo);
		return result;
	}

	@Override 
	public List<Calender> selectTodoByTeam(Integer teamNo) {
		List<Calender> cList = cMapper.selectTodoByTeam(teamNo);
		return cList;
	}

	@Override
	public List<Team> selectUserByTeam(Integer teamNo) {
		List<Team> users = cMapper.selectUserByTeam(teamNo);
		return users;
	}

	@Override
	public List<UserTeam> selectUsersByTeam(Integer teamNo) {
		List<UserTeam> users = cMapper.selectUsersByTeam(teamNo);
		return users;
	}

	@Override
	public List<Calender> selectListByNo(Integer teamNo) {
		List<Calender> cList = cMapper.selectListByNo(teamNo);
		return cList;
	}

	@Override
	public int modifyCalender(Calender calender) {
		int result = cMapper.modifyCalender(calender);
		return result;
	}
}
