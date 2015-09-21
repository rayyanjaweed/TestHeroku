package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.lakeshoremarket.model.customer.Address;
import com.online.lakeshoremarket.model.customer.Customer;
import com.online.lakeshoremarket.util.DatabaseConnection;

public class CustomerDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public int addCustomer(Customer cust){
		conn = DatabaseConnection.getSqlConnection();
		int rowsUpdated = 0;
		try{
			String insertStmt = "INSERT INTO customer "
											+ "(ship_address_id, bill_address_id, tel, email, name, title, password, paypal_cust_id) "
								+ "VALUES "
											+ "(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, cust.getShippingAddress());
			pstmt.setInt(2, cust.getBillingAddress());
			pstmt.setString(3, cust.getPhone());
			pstmt.setString(4, cust.getEmail());
			pstmt.setString(5, cust.getFirstName());
			pstmt.setString(6, cust.getTitle());
			pstmt.setString(7, cust.getPassword());
			pstmt.setString(8, cust.getPaypalCustID());
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("CustomerDAO.addCustomer: Threw a SQLException inserting a new customer in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("CustomerDAO.addCustomer: Threw an Exception inserting a new customer in table.");
			}
		}
		return rowsUpdated;
	}

	public int addAddress(Address address) {
		conn = DatabaseConnection.getSqlConnection();
		int addressID = 0;
		int rowsUpdated = 0;
		try{
			String insertStmt = "INSERT INTO address "
											+ "(line1, line2, line3, city, state, country, postal_code) "
								+ "VALUES "
											+ "(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setString(1, address.getLine1());
			pstmt.setString(2, address.getLine2());
			pstmt.setString(3, address.getLine3());
			pstmt.setString(4, address.getCity());
			pstmt.setString(5, address.getState());
			pstmt.setString(6, address.getCountry());
			pstmt.setString(7, address.getZip());
			rowsUpdated = pstmt.executeUpdate();
			if(0 != rowsUpdated){
				String selectQuery = "SELECT MAX(address_id) FROM address";
				pstmt = conn.prepareStatement(selectQuery);
				ResultSet resultSet = pstmt.executeQuery();
				if(resultSet.next()){
					addressID = resultSet.getInt(1);
				}
			}
		}catch(SQLException sqe){
			System.err.println("CustomerDAO.addAddress: Threw a SQLException inserting a new address in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("CustomerDAO.addAddress: Threw an Exception inserting a new address in table.");
			}
		}
		return addressID;
	}

	public void deleteCustomer(int custID) {
		conn = DatabaseConnection.getSqlConnection();
		int rowsUpdated = 0;
		/*try{
			String deleteStmt = "INSERT INTO customer "
											+ "(ship_address_id, bill_address_id, tel, email, name, title, password, paypal_cust_id) "
								+ "VALUES "
											+ "(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, cust.getShippingAddress());
			pstmt.setInt(2, cust.getBillingAddress());
			pstmt.setString(3, cust.getPhone());
			pstmt.setString(4, cust.getEmail());
			pstmt.setString(5, cust.getFirstName());
			pstmt.setString(6, cust.getTitle());
			pstmt.setString(7, cust.getPassword());
			pstmt.setString(8, cust.getPaypalCustID());
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("CustomerDAO.addCustomer: Threw a SQLException inserting a new customer in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("CustomerDAO.addCustomer: Threw an Exception inserting a new customer in table.");
			}
		}*/
//		return rowsUpdated;
		
	}
}
