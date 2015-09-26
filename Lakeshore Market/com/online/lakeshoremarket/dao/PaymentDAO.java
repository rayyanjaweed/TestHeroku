package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.lakeshoremarket.model.payment.Payment;
import com.online.lakeshoremarket.util.DatabaseConnection;

public class PaymentDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	public int createPayment(Payment custPayment) {
		int paymentID = 0;
		conn = DatabaseConnection.getSqlConnection();
		try{
			String insertStmt = "INSERT INTO payment "
											+ "(status_id, method, method_transaction_id, date_paid, date_refunded, total_paid ) "
								+ "VALUES "
											+ "(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, custPayment.getPaymentStatusCode());
			pstmt.setString(2, String.valueOf(custPayment.getMethodOfPayment()));
			pstmt.setInt(3, custPayment.getMethodTransactionID());
			pstmt.setTimestamp(4, custPayment.getDatePaid());
			pstmt.setTimestamp(5, custPayment.getDateReturned());
			pstmt.setFloat(6, custPayment.getTotalPaid());
			pstmt.executeUpdate();
			
			
			String getQuery = "SELECT MAX(payment_id) FROM payment ";
			pstmt = conn.prepareStatement(getQuery);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				paymentID = resultSet.getInt(1);
			}
		}catch(SQLException sqe){
			System.err.println("PaymentDAO.createPayment: Threw a SQLException inserting a new payment in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("PaymentDAO.createPayment: Threw an Exception inserting a new payment in table.");
			}
		}
		return paymentID;
	}

}
