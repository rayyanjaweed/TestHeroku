package com.online.lakeshoremarket.model.payment;

import java.util.Date;

public interface Payment {

	public Date getDatePaid();
	public void setDatePaid(Date datePaid);
	public Date getDateReturned();
	public void setDateReturned(Date dateReturned);
	public float getTotalPaid();
	public void setTotalPaid(float totalPaid);
	public int getPaymentID();
	public void setPaymentID(int paymentID);
	public int getPaymentStatusCode();
	public void setPaymentStatusCode(int paymentStatusCode);
	public String getMethodOfPayment();
	public void setMethodOfPayment(String methodOfPayment);
	public String getMethodTransactionID();
	public void setMethodTransactionID(String methodTransactionID);
	
	
}
