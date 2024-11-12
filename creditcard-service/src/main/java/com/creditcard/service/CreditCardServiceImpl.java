
package com.creditcard.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.creditcard.exception.CreditCardNotFoundException;
import com.creditcard.model.Currency;
import com.creditcard.model.CreditCard;


@Repository
public class CreditCardServiceImpl implements CreditCardService {

	private static ConcurrentHashMap<String, CreditCard> creditcards;

	static {
		creditcards = new ConcurrentHashMap<>();
		creditcards.putAll(initializeCreditCard());
	}

	public CreditCard getCreditCard(String id) {
		if (creditcards.containsKey(id)) {
			return creditcards.get(id);
		} else {
			throw new CreditCardNotFoundException("Id not found: " + id);
		}
	}

	@Override
	public Collection<CreditCard> get() {
		System.out.println("Fetch all details....");
		return creditcards.values();
	}

	private static ConcurrentHashMap<String, CreditCard> initializeCreditCard() {
		ConcurrentHashMap<String, CreditCard> creditcards = new ConcurrentHashMap<>();

		CreditCard deepti = new CreditCard();
		deepti.setId("1");
		deepti.setBalance(170000.00);
		deepti.setCurrency(Currency.INR);

		CreditCard krish = new CreditCard();
		krish.setId("2");
		krish.setBalance(70000.00);
		krish.setCurrency(Currency.INR);

		CreditCard payal = new CreditCard();
		payal.setId("3");
		payal.setBalance(91000.00);
		payal.setCurrency(Currency.INR);
		
		CreditCard riya = new CreditCard();
		riya.setId("4");
		riya.setBalance(170000.00);
		riya.setCurrency(Currency.INR);

		CreditCard sahil = new CreditCard();
		sahil.setId("5");
		sahil.setBalance(70000.00);
		sahil.setCurrency(Currency.INR);

		CreditCard piyush = new CreditCard();
		piyush.setId("6 ");
		piyush.setBalance(91000.00);
		piyush.setCurrency(Currency.INR);

		creditcards.put("1", deepti);
		creditcards.put("2", krish);
		creditcards.put("3", payal);
		creditcards.put("4", riya);
		creditcards.put("5", sahil);
		creditcards.put("6", piyush);

		return creditcards;
	
	}

	
}
