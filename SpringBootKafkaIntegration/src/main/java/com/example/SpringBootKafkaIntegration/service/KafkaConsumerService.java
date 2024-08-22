package com.example.SpringBootKafkaIntegration.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	
	@KafkaListener(topics="javainuse-topic",groupId="group_id")
	public void consume(String message) {
		System.out.println("From Consumer :: "+message);
		
		//Uncomment Following Exception Code to Test DLQ
		throw new NullPointerException();
	}
}
