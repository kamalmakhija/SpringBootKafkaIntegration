package com.example.SpringBootKafkaIntegration.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put("security.protocol", "SSL");
        configProps.put("ssl.keystore.location", "C:/Users/Kamal/ssl-certs/kafka.server.keystore.jks");
        configProps.put("ssl.keystore.password", "kamalm");
        configProps.put("ssl.key.password", "kamal");
        configProps.put("ssl.truststore.location", "C:/Users/Kamal/ssl-certs/kafka.server.truststore.jks");
        configProps.put("ssl.truststore.password", "kamalm");
        configProps.put("spring.dead-letter-topic", "test-dlq-topic");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
