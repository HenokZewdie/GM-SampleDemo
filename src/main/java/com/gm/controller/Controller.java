package com.gm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gm.model.OnStarProfileSubscription;
import com.gm.model.SubscriptionAndUserDetailsToStoreIntoTheDB;
import com.gm.model.User;
import com.gm.model.Users;
import com.gm.service.AccountBillingService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

public class Controller {

	private static final String TOPIC = "Kafka_Example";
	
	@Autowired
	private KafkaTemplate<String, SubscriptionAndUserDetailsToStoreIntoTheDB> kafkaTemplate;

	@Autowired
	AccountBillingService service;

	@Autowired
	MongoTemplate mongoTemplate;

	@RequestMapping(value = "/getUsersDetails", method = RequestMethod.GET)
	public String getAllUser() {
		String response = "ERROR on STORING TO THE DB";
		try {
			SubscriptionAndUserDetailsToStoreIntoTheDB detailsToStoreIntoTheDB = service.getUsersDetails();
			mongoTemplate.save(detailsToStoreIntoTheDB);
			response = "Successfully stored in the DB";
		} catch (Exception e) {

		}
		return response;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public SubscriptionAndUserDetailsToStoreIntoTheDB post() throws JsonParseException, JsonMappingException, IOException {
		SubscriptionAndUserDetailsToStoreIntoTheDB detailsToStoreIntoTheDB = service.getUsersDetails();
		kafkaTemplate.send(TOPIC, detailsToStoreIntoTheDB);

		return detailsToStoreIntoTheDB;
	}

    @KafkaListener(topics = "OnStarService", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public OnStarProfileSubscription consumeJson(OnStarProfileSubscription  onStarProfileSubscription) {
        System.out.println("Consumed JSON Message: " + onStarProfileSubscription.getUserId());
        return onStarProfileSubscription;
    }
    
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public User registerUser(@RequestBody User userReceived) {

		try {
			mongoTemplate.save(userReceived);

		} catch (Exception e) {

		}
		return null;/*
					 * mongoTemplate.findById(userReceived.getUsername(),
					 * User.class);
					 */
	}

	@RequestMapping(value = "/viewUser", method = RequestMethod.POST)
	public User viewUser(@RequestBody String username) {
		User user = mongoTemplate.findById(username, User.class);
		return user;
	}

}
