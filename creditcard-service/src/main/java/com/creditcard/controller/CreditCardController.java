package com.creditcard.controller;

import java.io.IOException;
import java.util.Collection;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.model.CreditCard;
import com.creditcard.model.CreditCardUpdate;
import com.creditcard.service.CreditCardService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@EnableBinding(Source.class)
@RequestMapping("creditcards")
public class CreditCardController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	@Autowired
	private CreditCardService creditcardService;

	
	@Autowired
	CreditCardController creditcardupdateService;
	
	@Autowired
	Source source;
	
	
	@PostMapping("/{update}")
	public String update(@RequestBody String payload) {
		ObjectMapper ob=new ObjectMapper();
		CreditCardUpdate update = null;
		try {
			update = ob.readValue(payload, CreditCardUpdate.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		source.output().send(MessageBuilder.withPayload(update).setHeader("myheader","myheaderValue").build());
	    System.out.println("Sucessfully sent to rabbitmq:");
		return "Success";
	}

	@GetMapping("/{id}")
	public ResponseEntity<CreditCard> getCreditCard(@PathVariable String id) {
		logger.info("Notification: Request Received for CreditCard Id: {}", id);
		return ResponseEntity.ok(creditcardService.getCreditCard(id));
	}

	@GetMapping()
	public ResponseEntity<Collection<CreditCard>> get() {
		return ResponseEntity.ok(creditcardService.get());
	}


}
