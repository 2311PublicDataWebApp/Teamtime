package com.teamtime.tt.vote.model.dto;

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
public class VoteOption {
	@NonNull
	private Integer voteOptionNo;
	private Integer voteNo;
	private String voteOptionContent;
}
