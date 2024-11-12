
package com.creditcard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CreditCard {

	private String id;
	
	@JsonIgnore
	private String customerId;
	private double balance;
	private Currency currency;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", customerId=" + customerId + ", balance=" + balance + ", currency=" + currency
				+ "]";
	}

	
}
