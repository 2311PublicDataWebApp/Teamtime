package com.teamtime.tt.notice.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticePageInfo {

	private int currentPage;
    private int totalCount;
    private int naviTotalCount;
    private int boardLimit;
    private int naviLimit;
    private int startNavi;
    private int endNavi;
    
    public NoticePageInfo() {
    }


    public NoticePageInfo(int currentPage, int totalCount, int naviTotalCount, int boardLimit, int naviLimit, int startNavi, int endNavi) {
    	this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.naviTotalCount = naviTotalCount;
		this.boardLimit = boardLimit;
		this.naviLimit = naviLimit;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
    }
}
