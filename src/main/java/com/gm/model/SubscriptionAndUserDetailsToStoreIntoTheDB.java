package com.gm.model;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriptionAndUserDetailsToStoreIntoTheDB")
public class SubscriptionAndUserDetailsToStoreIntoTheDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
