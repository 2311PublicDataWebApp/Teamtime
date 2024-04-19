package com.teamtime.tt.ask.model.dto;

import java.util.Date;

public class AskFileVO {
    private int fileNo;
    private int askNo;
    private String fileName;
    private String fileRename;
    private String filePath;
    private long fileLength;
    private Date fileDate;
    
    public AskFileVO() {
    }
    
    

	public AskFileVO(int fileNo, int askNo, String fileName, String fileRename, String filePath, long fileLength,
			Date fileDate) {
		super();
		this.fileNo = fileNo;
		this.askNo = askNo;
		this.fileName = fileName;
		this.fileRename = fileRename;
		this.filePath = filePath;
		this.fileLength = fileLength;
		this.fileDate = fileDate;
	}



	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getAskNo() {
		return askNo;
	}

	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	@Override
	public String toString() {
		return "AskFileVO [fileNo=" + fileNo + ", askNo=" + askNo + ", fileName=" + fileName + ", fileRename="
				+ fileRename + ", filePath=" + filePath + ", fileLength=" + fileLength + ", fileDate=" + fileDate + "]";
	}
    
    
}
