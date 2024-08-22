package com.example.SpringBootKafkaIntegration.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDLQConsumerService {

	@KafkaListener(topics="test-dlq-topic",groupId="group_id")
	public void listenDLQ(String message) {
		System.out.println("From DLQ :: "+message);
	}
}
