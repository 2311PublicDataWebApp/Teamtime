package com.teamtime.tt.ask.model.dto;

import java.util.Date;

public class AskVO {
    private int askNo;
    private String askTitle;
    private String askContent;
    private String askWriter;
    private Date askCreateDate;
    private String secretYn;

    public AskVO() {
    }
    
    

	public AskVO(int askNo, String askTitle, String askContent, String askWriter, Date askCreateDate, String secretYn) {
		super();
		this.askNo = askNo;
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askWriter = askWriter;
		this.askCreateDate = askCreateDate;
		this.secretYn = secretYn;
	}



	public int getAskNo() {
		return askNo;
	}

	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}

	public String getAskTitle() {
		return askTitle;
	}

	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}

	public String getAskContent() {
		return askContent;
	}

	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}

	public String getAskWriter() {
		return askWriter;
	}

	public void setAskWriter(String askWriter) {
		this.askWriter = askWriter;
	}

	public Date getAskCreateDate() {
		return askCreateDate;
	}

	public void setAskCreateDate(Date askCreateDate) {
		this.askCreateDate = askCreateDate;
	}

	public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}

	@Override
	public String toString() {
		return "Ask [askNo=" + askNo + ", askTitle=" + askTitle + ", askContent=" + askContent + ", askWriter="
				+ askWriter + ", askCreateDate=" + askCreateDate + ", secretYn=" + secretYn + "]";
	}
    
}
