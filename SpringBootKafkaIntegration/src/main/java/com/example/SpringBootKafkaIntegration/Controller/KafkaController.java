package com.example.SpringBootKafkaIntegration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootKafkaIntegration.service.KafkaProducerService;

@RestController
public class KafkaController {

	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	// Type http://localhost:8080/callKafkaProducer in browser to call this
	@RequestMapping("/callKafkaProducer")
	public String callKafkaProducer() {
		
		String topic ="javainuse-topic";
		String message="Hello Kafka";
		
		kafkaProducerService.sendMessage(topic, message);
		return "Success";
	}
	
}
