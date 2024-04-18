package com.teamtime.tt.ask.model.dto;

import java.util.Date;

public class ReplyVO {
    private int replyNo;
    private int replyAskNo;
    private String replyContent;
    private String replyWriter;
    private Date replyCreateDate;
    
    public ReplyVO() {
    }
    
    

	public ReplyVO(int replyNo, int replyAskNo, String replyContent, String replyWriter, Date replyCreateDate) {
		super();
		this.replyNo = replyNo;
		this.replyAskNo = replyAskNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.replyCreateDate = replyCreateDate;
	}



	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getReplyAskNo() {
		return replyAskNo;
	}

	public void setReplyAskNo(int replyAskNo) {
		this.replyAskNo = replyAskNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getReplyCreateDate() {
		return replyCreateDate;
	}

	public void setReplyCreateDate(Date replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", replyAskNo=" + replyAskNo + ", replyContent=" + replyContent
				+ ", replyWriter=" + replyWriter + ", replyCreateDate=" + replyCreateDate + "]";
	}
    
    

}
