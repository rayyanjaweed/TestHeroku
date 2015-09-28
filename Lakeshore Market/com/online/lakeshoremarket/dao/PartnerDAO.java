package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.lakeshoremarket.model.customer.Address;
import com.online.lakeshoremarket.model.partner.Partner;
import com.online.lakeshoremarket.util.DatabaseConnection;

public class PartnerDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	public int addPartner(Partner partner) {
		conn = DatabaseConnection.getSqlConnection();
		int rowsUpdated = 0;
		try{
			String insertStmt = "INSERT INTO partner "
											+ "(address_id, name, contact_name, tel, email, password, active) "
								+ "VALUES "
											+ "(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, partner.getAddressID());
			pstmt.setString(2, partner.getPartnerName());
			pstmt.setString(3, partner.getContactName());
			pstmt.setString(4, partner.getPhone());
			pstmt.setString(5, partner.getEmail());
			pstmt.setString(6, partner.getPassword());
			pstmt.setBoolean(7, partner.isActive());
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("PartnerDAO.addPartner: Threw a SQLException inserting a new partner in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("PartnerDAO.addPartner: Threw an Exception inserting a new partner in table.");
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
			System.err.println("PartnerDAO.addAddress: Threw a SQLException inserting a new address in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("PartnerDAO.addAddress: Threw an Exception inserting a new address in table.");
			}
		}
		return addressID;
	}

	public boolean deletePartner(int partnerID) {
		int rowsUpdated = 0;
		boolean isPartnerDeleted = false;
		conn = DatabaseConnection.getSqlConnection();
		try{
			String updateStmt = "UPDATE partner SET active = 0 WHERE partner_id = ?";
			pstmt = conn.prepareStatement(updateStmt);
			pstmt.setInt(1, partnerID);
			rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated == 1){
				isPartnerDeleted = true;
			}
		}catch(SQLException sqe){
			System.err.println("PartnerDAO.deletePartner: Threw a SQLException deleting partner from the table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("PartnerDAO.deletePartner: Threw an Exception deleting partner from the table.");
			}
		}
		return isPartnerDeleted;
	}

	public boolean getStatus(int partnerID) {
		conn = DatabaseConnection.getSqlConnection();
		boolean isPartnerActive = false;
		try{
			String getQuery = "SELECT active FROM partner WHERE partner_id = ? ";
			pstmt = conn.prepareStatement(getQuery);
			pstmt.setInt(1, partnerID);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				isPartnerActive = resultSet.getBoolean("active");
			}
		}catch(SQLException sqe){
			System.err.println("PartnerDAO.getStatus: Threw a SQLException while getting partner active status.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("PartnerDAO.getStatus: Threw a Exception while getting partner active status.");
			}
		}
		return isPartnerActive;
	}

}