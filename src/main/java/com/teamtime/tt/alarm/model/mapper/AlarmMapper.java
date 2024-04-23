package com.teamtime.tt.alarm.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.alarm.model.dto.Alarm;

@Mapper
public interface AlarmMapper {

	List<Alarm> selectAllAlarm(String userId);

	List<Alarm> selectUnreadAlarm(String userId);

	int insertAlarm(Alarm alarm);

	int updateAllAlarmIsRead(String userId);

	int deleteAllAlarm(String userId);

	int deleteAlarmByNo(Integer alarmNo);



}
