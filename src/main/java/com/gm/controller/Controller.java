package com.gm.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gm.model.OnStarProfileSubscription;
import com.gm.model.SubscriptionAndUserDetailsToStoreIntoTheDB;
import com.gm.model.User;
import com.gm.service.AccountBillingService;

@RestController
public class Controller {

	private static final String TOPIC = "Kafka_Example";
	
	@Autowired
	private KafkaTemplate<String, SubscriptionAndUserDetailsToStoreIntoTheDB> kafkaTemplate;

	@Autowired
	AccountBillingService service;

	@Autowired
	MongoTemplate mongoTemplate;

	//@RequestMapping(value = "/getUsersDetails", method = RequestMethod.GET)
	public SubscriptionAndUserDetailsToStoreIntoTheDB getAllUser(String userId) throws JsonParseException, JsonMappingException, IOException {
		String response = "ERROR on STORING TO THE DB";
		SubscriptionAndUserDetailsToStoreIntoTheDB detailsToStoreIntoTheDB = null;
		try {
			//TODO... SAGA
			detailsToStoreIntoTheDB = service.getUsersDetails(userId);
			mongoTemplate.save(detailsToStoreIntoTheDB);
			response = "Successfully stored in the DB";
		} catch (Exception e) {

		}
		
		post(detailsToStoreIntoTheDB);
		return detailsToStoreIntoTheDB;
	}

	//TODO ... instead of calling the api, pass the SubscriptionAndUserDetailsToStoreIntoTheDB to the method to publish
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public SubscriptionAndUserDetailsToStoreIntoTheDB post(SubscriptionAndUserDetailsToStoreIntoTheDB detailsToStoreIntoTheDB) throws JsonParseException, JsonMappingException, IOException {
		//No need to use the next line if SubscriptionAndUserDetailsToStoreIntoTheDB is passed
		//SubscriptionAndUserDetailsToStoreIntoTheDB detailsToStoreIntoTheDB = service.getUsersDetails(userId);
		kafkaTemplate.send(TOPIC, detailsToStoreIntoTheDB);

		return detailsToStoreIntoTheDB;
	}

    @KafkaListener(topics = "OnStarService", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public SubscriptionAndUserDetailsToStoreIntoTheDB consumeJson(OnStarProfileSubscription  onStarProfileSubscription) throws JsonParseException, JsonMappingException, IOException {
        System.out.println("Consumed JSON Message: " + onStarProfileSubscription.getUserId());
        SubscriptionAndUserDetailsToStoreIntoTheDB  detailsToStoreIntoTheDB =  getAllUser(onStarProfileSubscription.getUserId());
        return detailsToStoreIntoTheDB;
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
