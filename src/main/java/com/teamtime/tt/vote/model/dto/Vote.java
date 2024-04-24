package com.teamtime.tt.vote.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Vote {

	@NonNull
	private Integer voteNo;
	@NonNull
	private Integer teamNo;
	private String voteTitle;
	private Date voteStartDate;
	private Date voteEndDate;
	private String voteEndYn;
	private String duplicateYn;
	private String resultYn;
	private String anonymousYn;
}
