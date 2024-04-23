package com.teamtime.tt.alarm.model.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.mapper.AlarmMapper;
import com.teamtime.tt.alarm.model.service.AlarmService;

@Service
public class AlarmServiceLogic implements AlarmService{
	
	@Autowired
	private AlarmMapper aMapper;

	@Override
	public List<Alarm> selectAllAlarm(String userId) {
		List<Alarm> aList = aMapper.selectAllAlarm(userId);
		return aList;
	}

	@Override
	public List<Alarm> selectUnreadAlarm(String userId) {
		List<Alarm> unreadAlarmList = aMapper.selectUnreadAlarm(userId);
		return unreadAlarmList;
	}
	
	@Override
	public int insertAlarm(Alarm alarm) {
		int result = aMapper.insertAlarm(alarm);
		return result;
	}

	@Override
	public int updateAllAlarmIsRead(String userId) {
		int result = aMapper.updateAllAlarmIsRead(userId);
		return result;
	}

	@Override
	public int deleteAllAlarm(String userId) {
		int result = aMapper.deleteAllAlarm(userId);
		return result;
	}

	@Override
	public int deleteAlarmByNo(Integer alarmNo) {
		int result = aMapper.deleteAlarmByNo(alarmNo);
		return result;
	}
}
