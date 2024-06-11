package com.HotelManagement.model;

import java.io.Serializable;

public class User {
	protected int id;
	protected String name;
	protected String email;
	protected String country;
	protected String role;
	
	public User() {
	}
	
	public User(String name, String email, String country, String role) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.role = role;
	}

	public User(int id, String name, String email, String country, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
