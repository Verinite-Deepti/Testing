package com.customerservice.model;

public class CreditCardUpdate {

	
	private String id;
	private String cardNumber;
	private String status;
	
	public CreditCardUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CreditCardUpdate(String id, String cardNumber, String status) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.status = status;
	}
	
	
	
}
