package com.teamtime.tt.vote.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class needToDeleteVote {
	@NonNull
	private Integer voteNo;
	@NonNull
	private String userId;
}
