package com.creditcard.service;

import java.util.Collection;

import com.creditcard.model.CreditCard;


public interface CreditCardService {

	/**
	 * Return the creditcard of the customer
	 * 
	 * @param id
	 *            id of the customer
	 * @return creditcard detail
	 */
	public CreditCard getCreditCard(String id);

	/**
	 * Returns all the creditcard.
	 * 
	 * @return list of all creditcard
	 */
	public Collection<CreditCard> get();
}
