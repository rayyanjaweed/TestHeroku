package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.online.lakeshoremarket.model.order.Order;
import com.online.lakeshoremarket.util.Constant;
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

	public boolean shipOrder(int orderID, String trackingNumber) {
		int rowsUpdated = 0;
		conn = DatabaseConnection.getSqlConnection();
		try{
			String updateStmt = "UPDATE `order` SET tracking_number = ?, status_id = ? WHERE order_id = ?";
			pstmt = conn.prepareStatement(updateStmt);
			pstmt.setString(1, trackingNumber);
			pstmt.setInt(2, Constant.SHIPPED);
			pstmt.setInt(3, orderID);
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("OrderDAO.shipOrder: Threw a SQLException updating tracking number and status in the order table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("OrderDAO.shipOrder: Threw an Exception updating tracking number and status in the order table.");
			}
		}
		return (rowsUpdated == 0)? false : true;
	}

	public boolean fulfillOrder(int orderID) {
		int rowsUpdated = 0;
		conn = DatabaseConnection.getSqlConnection();
		try{
			String updateStmt = "UPDATE `order` SET status_id = ? WHERE order_id = ?";
			pstmt = conn.prepareStatement(updateStmt);
			pstmt.setInt(1, Constant.DELIVERED);
			pstmt.setInt(2, orderID);
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("OrderDAO.fulfillOrder: Threw a SQLException updating tracking number and status in the order table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("OrderDAO.fulfillOrder: Threw an Exception updating tracking number and status in the order table.");
			}
		}
		return (rowsUpdated == 0)? false : true;
	}

}
