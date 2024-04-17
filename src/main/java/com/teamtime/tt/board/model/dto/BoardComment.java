package com.teamtime.tt.board.model.dto;

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
public class BoardComment {
	private Integer commentNo;
	private Integer boardNo;
	private String userId;
	private String commentContent;
	private Date commentDate;
	private Date updateDate;
	private String updateYn;
}
