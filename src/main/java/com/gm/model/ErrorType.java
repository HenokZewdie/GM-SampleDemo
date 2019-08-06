package com.gm.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ErrorType {
	@SerializedName("code")
	private String code = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("subCode")
	private String subCode = null;

	@SerializedName("subCodeDescription")
	private String subCodeDescription = null;

	@SerializedName("moreInfo")
	private String moreInfo = null;

	@SerializedName("additionalInfo")
	private List<String> additionalInfo = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubCodeDescription() {
		return subCodeDescription;
	}

	public void setSubCodeDescription(String subCodeDescription) {
		this.subCodeDescription = subCodeDescription;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public List<String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(List<String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
