package com.teamtime.tt.calender.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.calender.model.dto.Calender;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;

@Mapper
public interface CalenderMapper {

	/**
	 * 팀 일정 등록
	 * @param calender
	 * @return
	 */
	int insertCalender(Calender calender);

	/**  
	 * 팀 일정 삭제
	 * @param calenderNo
	 * @return int
	 */
	int deleteByNo(Integer calenderNo);

	/**
	 * 팀 일정 조회
	 * @param teamNo
	 * @return
	 */
	List<Calender> selectTodoByTeam(Integer teamNo);

	/**
	 * 리더 조회
	 * @param teamNo
	 * @return
	 */
	List<Team> selectUserByTeam(Integer teamNo);

	/**
	 * 팀원 조회
	 * @param teamNo
	 * @return
	 */
	List<UserTeam> selectUsersByTeam(Integer teamNo);

	/**
	 * 일정 조회
	 * @param teamNo
	 * @return List<Calender>
	 */
	List<Calender> selectListByNo(Integer teamNo);

	/**
	 * 일정 수정
	 * @param calender
	 * @return
	 */
	int modifyCalender(Calender calender);
}
