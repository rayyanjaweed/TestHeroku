package com.online.lakeshoremarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.online.lakeshoremarket.model.product.ProdImpl;
import com.online.lakeshoremarket.model.product.Product;
import com.online.lakeshoremarket.util.DatabaseConnection;

public class ProductDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public Product getProductByName(String prodName){
		Product prod = null;
		conn = DatabaseConnection.getSqlConnection();
		try{
			String searchQuery = "SELECT * FROM product WHERE name = ? AND active = 1 ";
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setString(1, prodName);
			ResultSet resultSet = pstmt.executeQuery();
			prod = new ProdImpl();
			while(resultSet.next()){
				prod.setProductID(resultSet.getInt("product_id"));
				prod.setPartnerID(resultSet.getInt("partner_id"));
				prod.setTaxonomyID(resultSet.getInt("taxonomy_id"));
				prod.setCost(resultSet.getFloat("cost"));
				prod.setPrice(resultSet.getFloat("price"));
				prod.setProductName(resultSet.getString("name"));
				prod.setDescription(resultSet.getString("description"));
				prod.setQoh(resultSet.getInt("qoh"));
				prod.setActive(resultSet.getByte("active") == 1 ? true : false);
			}
		}catch(SQLException sqe){
			System.err.println("ProductDAO.getProductByName: Threw a SQLException while searching for a product in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("ProductDAO.getProductByName: Threw an Exception while searching for a product in table.");
			}
		}
		return prod;
	}
	
	public ArrayList<Product> getProductByLikeName(String prodName){
		Product prod = null;
		ArrayList<Product> prodList = new ArrayList<Product>();
		conn = DatabaseConnection.getSqlConnection();
		try{
			String searchQuery = "SELECT * FROM product WHERE name LIKE ? AND active = 1 ";
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setString(1, "%"+prodName+"%");
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				prod = new ProdImpl();
				prod.setProductID(resultSet.getInt("product_id"));
				prod.setPartnerID(resultSet.getInt("partner_id"));
				prod.setTaxonomyID(resultSet.getInt("taxonomy_id"));
				prod.setCost(resultSet.getFloat("cost"));
				prod.setPrice(resultSet.getFloat("price"));
				prod.setProductName(resultSet.getString("name"));
				prod.setDescription(resultSet.getString("description"));
				prod.setQoh(resultSet.getInt("qoh"));
				prod.setActive(resultSet.getByte("active") == 1 ? true : false);
				prodList.add(prod);
			}
		}catch(SQLException sqe){
			System.err.println("ProductDAO.getProductByLikeName: Threw a SQLException while searching for a product in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("ProductDAO.getProductByLikeName: Threw an Exception while searching for a product in table.");
			}
		}
		return prodList;
	}
	
	public int createProduct(Product prod){
		conn = DatabaseConnection.getSqlConnection();
		int rowsUpdated = 0;
		try{
			String insertStmt = "INSERT INTO product "
											+ "(partner_id,taxonomy_id,cost,price,name,description,qoh,active) "
								+ "VALUES "
											+ "(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertStmt);
			pstmt.setInt(1, prod.getPartnerID());
			pstmt.setInt(2, prod.getTaxonomyID());
			pstmt.setFloat(3, prod.getCost());
			pstmt.setFloat(4, prod.getPrice());
			pstmt.setString(5, prod.getProductName());
			pstmt.setString(6, prod.getDescription());
			pstmt.setInt(7, prod.getQoh());
			pstmt.setBoolean(8, prod.isActive());
			rowsUpdated = pstmt.executeUpdate();
		}catch(SQLException sqe){
			System.err.println("ProductDAO.createProduct: Threw a SQLException inserting a new product in table.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("ProductDAO.createProduct: Threw an Exception inserting a new product in table.");
			}
		}
		
		return rowsUpdated;
	}

	public Boolean getProductAvailability(String prodName) {
		Boolean isProductAvailable = false;
		conn = DatabaseConnection.getSqlConnection();
		int qoh = 0;
		try{
			String searchQuery = "SELECT qoh FROM product WHERE name = ? AND active = 1; ";
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setString(1, prodName);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				qoh = resultSet.getInt("qoh");
			}
			isProductAvailable = (qoh > 0 ) ?  true : false ;
		}catch(SQLException sqe){
			System.err.println("ProductDAO.getProductAvailability: Threw a SQLException while checking product availability.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("ProductDAO.getProductAvailability: Threw an Exception while checking product availability.");
			}
		}
		return isProductAvailable;
	}

	public Boolean getProductAvailabilityByID(int prodID) {
		Boolean isProductAvailable = false;
		conn = DatabaseConnection.getSqlConnection();
		int qoh = 0;
		try{
			String searchQuery = "SELECT qoh FROM product WHERE product_id = ? AND active = 1; ";
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, prodID);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				qoh = resultSet.getInt("qoh");
			}
			isProductAvailable = (qoh >= 0 ) ?  true : false ;
		}catch(SQLException sqe){
			System.err.println("ProductDAO.getProductAvailabilityByID: Threw a SQLException while checking product availability.");
  	      	System.err.println(sqe.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println("ProductDAO.getProductAvailabilityByID: Threw an Exception while checking product availability.");
			}
		}
		return isProductAvailable;
	}
}
