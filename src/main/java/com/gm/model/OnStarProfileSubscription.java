package com.gm.model;

public class OnStarProfileSubscription {

	private int EnrollmentId;
	private String userId = null;
	private int accountId;
	private String accountType = null;

	public int getEnrollmentId() {
		return EnrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		EnrollmentId = enrollmentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
