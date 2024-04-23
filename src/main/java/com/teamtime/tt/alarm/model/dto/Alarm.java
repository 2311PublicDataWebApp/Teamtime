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

}
