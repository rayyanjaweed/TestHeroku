package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.OrderDAO;
import com.online.lakeshoremarket.model.order.Order;

public class OrderDomain {

	OrderDAO orderDao = null;
	public void createOrder(Order custOrder) {
		orderDao = new OrderDAO();
		orderDao.createOrder(custOrder);
	}

}
