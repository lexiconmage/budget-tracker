package com.yearup;

import java.util.Date;

public class Expense extends Money {
//	expenseAmount, expenseName, date
	private float amount; 
	private String name; 
	
	public Expense(float amount, String name) {
		this.amount = amount;
		this.name = name;
	}
	
	public void setAmount(float val) {
		this.amount = val;
	}
	public void setName(String val) {
		this.name = val;
	}
	public float getAmount() {
		return this.amount;
	}
	public String getName() {
		return this.name;
	}
}

