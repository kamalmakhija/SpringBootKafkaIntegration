package com.example.SpringBootKafkaIntegration.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.ErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");

		return new DefaultKafkaConsumerFactory<>(configProps);
	}

	  @Bean
	    public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory(KafkaTemplate<String, String> kafkaTemplate) {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String,String>();
	        factory.setConsumerFactory(consumerFactory());
	        factory.setCommonErrorHandler(errorHandler(kafkaTemplate));
	        return factory;
	    }

	  @Bean
	  public DefaultErrorHandler errorHandler(KafkaTemplate<String, String> kafkaTemplate) {
	      // Configure DeadLetterPublishingRecoverer to send failed messages to a specific DLQ
	      DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
	          kafkaTemplate,
	          (record, exception) -> new TopicPartition("test-dlq-topic", record.partition()) // Specify the DLQ topic
	      );

	      // Set a FixedBackOff strategy (e.g., 2 retries with 1 second delay)
	      FixedBackOff fixedBackOff = new FixedBackOff(1000L, 2);

	      // Return a DefaultErrorHandler configured with the recoverer and backoff
	      return new DefaultErrorHandler(recoverer, fixedBackOff);
	  }
	  
}
