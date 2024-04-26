package com.teamtime.tt.team.model.dto;

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
@AllArgsConstructor
@RequiredArgsConstructor
public class UserJoinTeam {
	@NonNull
	private String userId;
	@NonNull
	private Integer teamNo;
	private String teamName;
}
