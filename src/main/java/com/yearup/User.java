package com.yearup;

public class User {
	String name;
	String email;
	String password;
	
	public User() {
		
	}
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public boolean login() {
		//TODO: implement'
		return false;
	}
	public boolean create() {
		//TODO: implement
		return false;
	}
}
