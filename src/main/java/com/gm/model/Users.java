package com.gm.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@Document(collection = "Users_Collection")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
	@SerializedName("size")
	private Long size = null;

	@SerializedName("offset")
	private Long offset = null;

	@SerializedName("limit")
	private Long limit = null;

	@SerializedName("results")
	private List<User> results = new ArrayList<User>();

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public List<User> getResults() {
		return results;
	}

	public void setResults(List<User> results) {
		this.results = results;
	}

}
