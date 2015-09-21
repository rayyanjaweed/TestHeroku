package com.online.lakeshoremarket.model.payment;

import java.util.Date;

public class PaymentImpl {

	private Date datePaid;
	private Date dateReturned;
	private float totalPaid;
	private int paymentID;
	private int paymentStatusCode;
	private String methodOfPayment;
	private String methodTransactionID;
	
	
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	public Date getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	public float getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(float totalPaid) {
		this.totalPaid = totalPaid;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getPaymentStatusCode() {
		return paymentStatusCode;
	}
	public void setStatusCode(int paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}
	public String getMethodOfPayment() {
		return methodOfPayment;
	}
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}
	public String getMethodTransactionID() {
		return methodTransactionID;
	}
	public void setMethodTransactionID(String methodTransactionID) {
		this.methodTransactionID = methodTransactionID;
	}
	
	
}
