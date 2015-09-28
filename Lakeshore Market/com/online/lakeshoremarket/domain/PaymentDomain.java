package com.online.lakeshoremarket.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.online.lakeshoremarket.dao.PaymentDAO;
import com.online.lakeshoremarket.model.cart.CartLine;
import com.online.lakeshoremarket.model.order.Order;
import com.online.lakeshoremarket.model.order.OrderImpl;
import com.online.lakeshoremarket.model.payment.Payment;
import com.online.lakeshoremarket.model.payment.PaymentImpl;
import com.online.lakeshoremarket.util.Constant;

/**
 * Represents the payment domain business logic
 *
 */
public class PaymentDomain {

	PaymentDAO pDao = null;
	
	/**
	 * orders a product if available
	 * @param prodID 	the id of the product purchased
	 * @param quantity 	the number of items ordered
	 * @param custID 	the id of the customer who purchased it
	 * @return 			order id or 0 on failure
	 */
	public int buyProduct(int prodID, int quantity, int custID) {
		boolean isProductAvailable = false;
		ProductDomain prodDomain = new ProductDomain();
		int methodTransactionID = 0;
		int orderID = 0;
		int price = 0;
		int paymentID = 0;
		isProductAvailable = prodDomain.checkProductAvailabilityByID(prodID);
		if(isProductAvailable){
			pDao = new PaymentDAO();
			Date systemDate = new Date();
			Timestamp date = new Timestamp(systemDate.getTime());
			price = prodDomain.getProductPrice(prodID);
			Payment custPayment = new PaymentImpl();
			methodTransactionID = acceptPayment(custPayment);		// This is PAYPAL method
			custPayment.setMethodTransactionID(methodTransactionID);
			custPayment.setMethodOfPayment(Constant.PAYPAL);
			custPayment.setPaymentStatusCode(Constant.PAID);
			custPayment.setDatePaid(date);
			custPayment.setTotalPaid(price * quantity);
			paymentID = pDao.createPayment(custPayment);
			prodDomain.decreaseQoh(prodID, quantity);
			OrderDomain orderDomain = new OrderDomain();
			Order custOrder = new OrderImpl();
			custOrder.setDatePurchased(date);
			custOrder.setOrderStatusCode(Constant.INPROGRESS);
			custOrder.setPaymentID(paymentID);
			custOrder.setTrackingNumber("Dummy Tracking Number");
			custOrder.setCustomerID(custID);
			orderDomain.createOrder(custOrder);
			
			
		}
		return orderID;
	}
	
	/**
	 * accepts a payment by a customer
	 * @uses				paypal API
	 * @param custPayment	the payment to accept
	 * @return				The transaction ID by the payment processor
	 */
	public int acceptPayment(Payment custPayment){
		int methodTransactionID = 0;
		//This method is left unimplemented intentionally. It will accommodate the payment acceptance for PAYPAL
		//It will return the Method Transaction ID
		return methodTransactionID;
	}

	/**
	 * refunds the customer
	 * @param paymentID 	the payment ID to refund
	 */
	public void refundCustomerMoney(int paymentID) {
		/*This method is implemented partially. This method will fetch payment details and then 
		return money through the PAYPAL. Finally it will update the payment status*/
		updatePaymentStatus(paymentID);
	}

	/**
	 * updates a payment status for a refund
	 * @param paymentID		the payment ID 
	 */
	private void updatePaymentStatus(int paymentID) {
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());
		pDao = new PaymentDAO();
		pDao.updatePaymentStatusForRefund(paymentID,date);
	}

	
}
