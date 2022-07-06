package com.yearup;

import java.util.Date;

public class Income extends Money {
	
	public Income() {
		
	}
	public Income(float value, String title, Date date) {
		this.value = value;
		this.title = title;
		this.date = date;
	}
}
