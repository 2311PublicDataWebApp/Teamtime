package com.teamtime.tt.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Board {
	private Integer boardNo;
	private Integer teamNo;
	private String userId;
	private String boardTitle;
	private String boardCategory;
	private String boardYouTube;
	private Date boardDate;
	private String boardContent;
}
