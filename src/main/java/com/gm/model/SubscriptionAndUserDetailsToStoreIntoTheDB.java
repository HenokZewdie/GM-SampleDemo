package com.gm.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriptionAndUserDetailsToStoreIntoTheDB")
public class SubscriptionAndUserDetailsToStoreIntoTheDB {

	private OnStarProfileSubscription onStarProfileSubscription;
	private Users users;

	public OnStarProfileSubscription getOnStarProfileSubscription() {
		return onStarProfileSubscription;
	}

	public void setOnStarProfileSubscription(OnStarProfileSubscription onStarProfileSubscription) {
		this.onStarProfileSubscription = onStarProfileSubscription;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
