package com.scht.front.bean;

import java.util.Date;

import com.scht.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ElinkResources{

	private String id;
	private String uuidName;
	private String fileName;
	private String fileType;
	private String fileRelativePath;
	private Integer fileSize;
	private Date createTime;
	private String status;
	private String travelAddressId;
	private String thumbnail;
	
	//逻辑字段
	private String travelAddressName;
	private String img;

	public ElinkResources() {
		super();
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUuidName() {
		return this.uuidName;
	}
	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileRelativePath() {
		return this.fileRelativePath;
	}
	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getTravelAddressId() {
		return travelAddressId;
	}

	public void setTravelAddressId(String travelAddressId) {
		this.travelAddressId = travelAddressId;
	}

	public String getTravelAddressName() {
		return travelAddressName;
	}

	public void setTravelAddressName(String travelAddressName) {
		this.travelAddressName = travelAddressName;
	}

	public String getImg() {
		if(!StringUtil.isNotNull(img))
			return fileRelativePath+uuidName;
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}