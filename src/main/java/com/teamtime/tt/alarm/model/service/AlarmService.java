package com.teamtime.tt.alarm.model.service;

import java.util.List;

import com.teamtime.tt.alarm.model.dto.Alarm;

public interface AlarmService {
	
	
	List<Alarm> selectAllAlarm(String userId);

	List<Alarm> selectUnreadAlarm(String userId);

	int insertAlarm(Alarm alarm);

	int updateAllAlarmIsRead(String userId);

	int deleteAllAlarm(String userId);

	int deleteAlarmByNo(String alarmNo);

}
