package com.teamtime.tt.alarm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
	@NonNull
	private String alarmNo;
	private String userId;
	private String senderId;
	private String alarmCode;
	private String alarmDate;
	private String alarmContent;
	private String isRead;
	
	public Alarm(String userId, String senderId, String alarmCode, String alarmDate, String alarmContent) {
		super();
		this.userId = userId;
		this.senderId = senderId;
		this.alarmCode = alarmCode;
		this.alarmDate = alarmDate;
		this.alarmContent = alarmContent;
	}
	
	public Alarm(String userId, String alarmCode, String alarmDate, String alarmContent) {
		super();
		this.userId = userId;
		this.alarmCode = alarmCode;
		this.alarmDate = alarmDate;
		this.alarmContent = alarmContent;
	}
	
	public Alarm(String senderId, String alarmCode, String alarmContent) {
		super();
		this.senderId = senderId;
		this.alarmCode = alarmCode;
		this.alarmContent = alarmContent;
	}

}
