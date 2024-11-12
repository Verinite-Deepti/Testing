package com.customerservice.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.customerservice.model.CreditCardUpdate;

@EnableBinding(Sink.class)
public class UpdateCreditCard {

	@StreamListener(Sink.INPUT)
	public void log(CreditCardUpdate update) {
		System.out.println("CreditCard contains"+ " Id: " + update.getId() + " " + " CardNumber: " +  update.getCardNumber() + " " + " Status: " + update.getStatus());
	}
	
}
