package com.teamtime.tt.calender.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Calender {
	private Integer calenderNo;
	private Integer teamNo;
	private String userId;
	private String startDate;
	private String endDate;
	private String calenderContent;
}
