package com.example.kafka;

import com.example.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {
    private static final Logger logger = LogManager.getLogger(UserConsumer.class);
    @KafkaListener(topics = "user-topic", groupId = "my-group-id")
    public void consume(User user) {
        logger.info("Received new user is :" + user.getUsername());
    }
}