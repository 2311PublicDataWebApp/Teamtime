package com.teamtime.tt.team.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.dto.UserJoinTeam;
import com.teamtime.tt.team.model.dto.UserTeam;
import com.teamtime.tt.user.model.dto.User;

@Mapper
public interface TeamMapper {

	/**
	 * 팀 번호로 팀 조회
	 * @param teamNo
	 * @return Team
	 */
	Team selectTeamByNo(Integer teamNo);

	/**
	 * 아이디로 팀 조회
	 * @param userId
	 * @return List<team>
	 */
	List<UserJoinTeam> selectTeamById(String userId);

	/**
	 * 팀에 속한 팀원들 조회
	 * @param teamNo
	 * @return List<User>
	 */
	List<User> selectUsersInTeam(Integer teamNo);

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

	/**
	 * 유저를 팀에 등록
	 * @param userId
	 * @return int
	 */
	int insertUserTeamByNo(UserJoinTeam ujt);

	/**
	 * 팀 수정
	 * @param team
	 * @return int
	 */
	int updateTeam(Team team);

	/**
	 * 유저 팀에서 탈퇴
	 * @param ut
	 * @return int
	 */
	int deleteUserInTeam(UserTeam ut);

	/**
	 * 팀 번호로 소속 팀원들 삭제
	 * @param teamNo
	 * @return int
	 */
	int deleteUsersInTeam(Integer teamNo);

}
