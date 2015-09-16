package com.online.lakeshoremarket.model.order;

import java.util.Date;

public class OrderImpl {

	private Date datePurchased;
	private Date dateRefunded;
	private int customerID;
	private int orderID;
	private int orderStatusCode;
	private int paymentID;
	private String trackingNumber;
	
	
	public Date getDatePurchased() {
		return datePurchased;
	}
	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}
	public Date getDateRefunded() {
		return dateRefunded;
	}
	public void setDateRefunded(Date dateRefunded) {
		this.dateRefunded = dateRefunded;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getOrderStatusCode() {
		return orderStatusCode;
	}
	public void setOrderStatusCode(int orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
	
}
