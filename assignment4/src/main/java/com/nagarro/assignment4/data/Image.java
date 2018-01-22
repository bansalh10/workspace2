package com.nagarro.assignment4.data;

import java.sql.Blob;

public class Image {
	private int imgId;
	private Blob imgData;
	private String imgName;
	private int imgSize;
	private User userId;

	public Image() {

	}

	public Image(final Blob imgData, final String imgName, final int imgSize, final User userId) {
		this.imgData = imgData;
		this.imgName = imgName;
		this.imgSize = imgSize;
		this.userId = userId;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(final int imgId) {
		this.imgId = imgId;
	}

	public Blob getImgData() {
		return imgData;
	}

	public void setImgData(final Blob imgData) {
		this.imgData = imgData;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(final String imgName) {
		this.imgName = imgName;
	}

	public int getImgSize() {
		return imgSize;
	}

	public void setImgSize(final int imgSize) {
		this.imgSize = imgSize;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(final User userId) {
		this.userId = userId;
	}

}
