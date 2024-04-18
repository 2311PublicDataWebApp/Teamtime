package com.teamtime.tt.ask.model.dto;

import java.util.Date;

public class AskImgVO {
    private int askImages;
    private String imageUrl;
    private Date imageCreateDate;
    private int askNo;
    
    public AskImgVO() {
    }

    
    
	public AskImgVO(int askImages, String imageUrl, Date imageCreateDate, int askNo) {
		super();
		this.askImages = askImages;
		this.imageUrl = imageUrl;
		this.imageCreateDate = imageCreateDate;
		this.askNo = askNo;
	}



	public int getAskImages() {
		return askImages;
	}

	public void setAskImages(int askImages) {
		this.askImages = askImages;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getImageCreateDate() {
		return imageCreateDate;
	}

	public void setImageCreateDate(Date imageCreateDate) {
		this.imageCreateDate = imageCreateDate;
	}

	public int getAskNo() {
		return askNo;
	}

	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}

	@Override
	public String toString() {
		return "AskImgVO [askImages=" + askImages + ", imageUrl=" + imageUrl + ", imageCreateDate=" + imageCreateDate
				+ ", askNo=" + askNo + "]";
	}
    
	
    
}
