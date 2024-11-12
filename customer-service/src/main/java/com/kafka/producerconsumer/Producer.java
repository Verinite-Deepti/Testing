package com.kafka.producerconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
public class Producer {
	
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	@GetMapping("/send")
	public void sendMesage(@RequestParam String message) {
		kafkaTemplate.send("customerservice",message);
	}
	
	
}
