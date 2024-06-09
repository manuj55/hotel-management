package com.HotelManagement.model;

import java.util.Date;

public class Reservations {
	protected int reservationID;
	protected int customerID;
	protected int categoryID;
	protected Date checkInDate;
	protected Date checkOutDate;
	
	public Reservations() {
	}
	
	public Reservations(int reservationID, int customerID, int categoryID, Date checkInDate, Date checkOutDate) {
		super();
		this.reservationID = reservationID;
		this.customerID = customerID;
		this.categoryID = categoryID;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	public int getReservationID() {
		return reservationID;
	}
	
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

}
