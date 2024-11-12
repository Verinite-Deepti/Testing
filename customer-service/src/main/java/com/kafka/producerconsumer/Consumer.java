package com.kafka.producerconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics= {"customerservice"},groupId="abc")
	public void consumeMessage(String message) {
		System.out.println(message);
		
	}
	
}
