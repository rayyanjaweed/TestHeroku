package com.online.lakeshoremarket.model.payment;

import java.sql.Timestamp;

public interface Payment {

	public Timestamp getDatePaid();
	public void setDatePaid(Timestamp datePaid);
	public Timestamp getDateReturned();
	public void setDateReturned(Timestamp dateReturned);
	public float getTotalPaid();
	public void setTotalPaid(float totalPaid);
	public int getPaymentID();
	public void setPaymentID(int paymentID);
	public int getPaymentStatusCode();
	public void setPaymentStatusCode(int paymentStatusCode);
	public char getMethodOfPayment();
	public void setMethodOfPayment(char methodOfPayment);
	public int getMethodTransactionID();
	public void setMethodTransactionID(int methodTransactionID);
	
	
}
