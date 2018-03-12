package com.tacoloco.mobileapp;

import java.util.List;

/**
 * (Bean) for Order. contains list of tacos, total amount, discount applied,
 * final amount, status code, and message.
 * 
 * @author pchakur
 *
 */
public class Order {

	List<Taco> tacos;

	double orderBillingAmount = 0.00;
	double discountApplied = 0.00;
	double finalAmount = 0.00;

	int statusCode = 200;
	String statusMessage = "OK";
	
	/**
	 * @return the tacos
	 */
	public List<Taco> getTacos() {
		return tacos;
	}

	/**
	 * @param tacos the tacos to set
	 */
	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}

	/**
	 * @return the orderBillingAmount
	 */
	public double getOrderBillingAmount() {
		return orderBillingAmount;
	}

	/**
	 * @param orderBillingAmount the orderBillingAmount to set
	 */
	public void setOrderBillingAmount(double orderBillingAmount) {
		this.orderBillingAmount = orderBillingAmount;
	}

	/**
	 * @return the discountApplied
	 */
	public double getDiscountApplied() {
		return discountApplied;
	}

	/**
	 * @param discountApplied the discountApplied to set
	 */
	public void setDiscountApplied(double discountApplied) {
		this.discountApplied = discountApplied;
	}

	/**
	 * @return the finalAmount
	 */
	public double getFinalAmount() {
		return finalAmount;
	}

	/**
	 * @param finalAmount the finalAmount to set
	 */
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		StringBuilder tacoDetails = new StringBuilder();
		int count = 1;
		for (Taco taco : tacos) {
			tacoDetails.append(taco.getTacoName() + " - " + taco.getQuantity());
			if (count < tacos.size()) {
				tacoDetails.append(", ");
			}
			count++;
		}
		return "Order [tacos = " + tacoDetails + " ] , total amount is - " + finalAmount;
	}

}
