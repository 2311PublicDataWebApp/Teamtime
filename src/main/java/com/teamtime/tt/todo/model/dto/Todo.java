package com.teamtime.tt.todo.model.dto;

import java.sql.Date;

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
	private Date startDate;
	private Date endDate;
	private String todoContent;
	private String todoStatus;
}
