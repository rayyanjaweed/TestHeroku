package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.online.lakeshoremarket.model.order.Order;
import com.online.lakeshoremarket.util.DatabaseConnection;

public class OrderDAO {

	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public void createOrder(Order custOrder) {
		conn = DatabaseConnection.getSqlConnection();
		try{
			String insertStmt = "INSERT INTO `order` "
											+ "(customer_id, payment_id, status_id, date_purchased, date_refunded, tracking_number ) "
								+ "VALUES "
											+ "(?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, custOrder.getCustomerID());
			pstmt.setInt(2, custOrder.getPaymentID());
			pstmt.setInt(3, custOrder.getOrderStatusCode());
			pstmt.setTimestamp(4, custOrder.getDatePurchased());
			pstmt.setTimestamp(5, custOrder.getDateRefunded());
			pstmt.setString(6, custOrder.getTrackingNumber());
			pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("OrderDAO.createOrder: Threw a SQLException inserting a new order in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("OrderDAO.createOrder: Threw an Exception inserting a new order in table.");
			}
		}
	}

}
