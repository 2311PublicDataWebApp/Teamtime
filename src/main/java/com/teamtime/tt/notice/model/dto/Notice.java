package com.teamtime.tt.notice.model.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {

	private int noticeNo;
	private String noticeName;
	private String noticeContent;
	private Date noticeDate;
	private Date updateDate;
	private String noticeFilename;
	private String noticeFileRename;
}
