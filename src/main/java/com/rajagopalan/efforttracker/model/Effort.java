package com.rajagopalan.efforttracker.model;

import java.time.LocalDate;

public class Effort {
	private double quantity;
	private LocalDate date;
	
	
	
	public Effort(double quantity, LocalDate date) {
		this.quantity = quantity;
		this.date = date;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	} 
	
	

}
