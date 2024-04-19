package com.teamtime.tt.todo.model.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Todo {
	private int todoNo;
	private String userId;
	private String startDate;
	private String endDate;
	private String todoContent;
	private String todoStatus;
}