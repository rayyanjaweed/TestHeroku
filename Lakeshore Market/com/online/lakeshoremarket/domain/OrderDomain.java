package com.online.lakeshoremarket.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.online.lakeshoremarket.dao.OrderDAO;
import com.online.lakeshoremarket.model.order.Order;
import com.online.lakeshoremarket.model.order.OrderImpl;
import com.online.lakeshoremarket.util.Constant;

public class OrderDomain {

	OrderDAO orderDao = null;
	public void createOrder(Order custOrder) {
		orderDao = new OrderDAO();
		orderDao.createOrder(custOrder);
	}
	public boolean shipOrder(int orderID, String trackingNumber) {
		boolean isOrderStatusUpdated = false;
		orderDao = new OrderDAO();
		isOrderStatusUpdated = orderDao.shipOrder(orderID, trackingNumber);
		return isOrderStatusUpdated;
	}
	public boolean fulfillOrder(int orderID) {
		boolean isOrderFulfilled = false;
		orderDao = new OrderDAO();
		isOrderFulfilled = orderDao.fulfillOrder(orderID);
		return isOrderFulfilled;
	}
	public int getOrderStatus(int orderID) {
		int orderStatus = -1;
		orderDao = new OrderDAO();
		orderStatus = orderDao.getOrderStatus(orderID);
		return orderStatus;
	}
	public boolean cancelAndRefundOrder(int orderID) {
		boolean isOrderRefunded = false;
		orderDao = new OrderDAO();
		Order custOrder = new OrderImpl();
		custOrder = orderDao.getOrderDetails(orderID);
		if(Constant.SHIPPED == custOrder.getOrderStatusCode()){
			notifyShipmentPartnerForRefund(custOrder);
		}
		PaymentDomain paymentDomain = new PaymentDomain();
		paymentDomain.refundCustomerMoney(custOrder.getPaymentID());
		updateOrderStatusForRefund(orderID);
		ProductDomain productDomain = new ProductDomain();
		//TODO get quantity and prodID from the order
		int quantity = 2;
		int prodID = 12;
		boolean isQuantityIncreased = false;
		isQuantityIncreased = productDomain.increaseQoh(prodID, quantity);
		if(isQuantityIncreased){
			isOrderRefunded = isQuantityIncreased;
		}
		return isOrderRefunded;
	}
	private void updateOrderStatusForRefund(int orderID) {
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());
		orderDao = new OrderDAO();
		orderDao.updateOrderStatusForRefund(orderID,date);
	}
	public void notifyShipmentPartnerForRefund(Order custOrder) {
		/*This method is being left unimplemented on purpose as we do not 
		have any means to communicate with the Shipment Delivery Partner. But this
		method would notify the Shipment Delivery Partner of cancellation of the order
		and refund of the products that are in due for shipment*/
	}

}
