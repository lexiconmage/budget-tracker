package com.yearup;

import java.util.Date;

public class Expense extends Money {
//	expenseAmount, expenseName, date
	private float expenseAmount; 
	private String expenseName; 
	private Date date;
	
	public Expense(float expenseAmount, String expenseName, Date date) {
		this.expenseAmount = expenseAmount;
		this.expenseName = expenseName;
		this.date = date;
	}
	
	public void setExpenseAmount(float val) {
		this.expenseAmount = val;
	}
	public void setExpenseName(String val) {
		this.expenseName = val;
	}
	public float getExpenseAmount() {
		return this.expenseAmount;
	}
	public String getExpenseName() {
		return this.expenseName;
	}
}

