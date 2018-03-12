package com.tacoloco.mobileapp;

/**
 * (Bean) for Taco. contains taco name and quantity ordered.
 * 
 * 
 * @author pchakur
 *
 */
public class Taco {

	String tacoName;
	int quantity;

	public String getTacoName() {
		return tacoName;
	}

	public void setTacoName(String tacoName) {
		this.tacoName = tacoName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int qty) {
		this.quantity = qty;
	}

	@Override
	public String toString() {
		return "Taco [Name=" + tacoName + ", quantity=" + quantity + "]";
	}

}
