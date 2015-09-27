package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.OrderDAO;
import com.online.lakeshoremarket.model.order.Order;

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

}
