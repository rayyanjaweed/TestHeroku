package com.online.lakeshoremarket.domain;

import java.util.ArrayList;

import com.online.lakeshoremarket.dao.ProductDAO;
import com.online.lakeshoremarket.model.product.Product;

/**
 * Represents the customer domain business logic
 *
 */
public class ProductDomain {

	ProductDAO pDao = null;
	
	public Product searchProductByName(String prodName){
		pDao = new ProductDAO();
		Product prod = null;
		prod = pDao.getProductByName(prodName);
		return prod;
	}
	
	public ArrayList<Product> searchProductByLikeName(String prodName){
		ArrayList<Product> prodList = new ArrayList<Product>();
		pDao = new ProductDAO();
		prodList = pDao.getProductByLikeName(prodName);
		return prodList;
	}
	
	public int addProduct(Product prod){
		int rowsUpdated = 0;
		pDao = new ProductDAO();
		rowsUpdated = pDao.createProduct(prod);
		return rowsUpdated;
	}

	public boolean checkProductAvailability(String prodName) {
		boolean isProductAvailable = false;
		pDao = new ProductDAO();
		isProductAvailable = pDao.getProductAvailability(prodName);
		return isProductAvailable;
	}
	
	public boolean checkProductAvailabilityByID(int prodID) {
		boolean isProductAvailable = false;
		pDao = new ProductDAO();
		isProductAvailable = pDao.getProductAvailabilityByID(prodID);
		return isProductAvailable;
	}

	public int getProductPrice(int prodID) {
		int price = 0;
		pDao = new ProductDAO();
		price = pDao.getProductPrice(prodID);
		return price;
	}

	public void decreaseQoh(int prodID, int quantity) {
		pDao = new ProductDAO();
		pDao.decreaseQoh(prodID, quantity);
	}

	public boolean increaseQoh(int prodID,int quantity) {
		boolean isQuantityIncreased = false;
		pDao = new ProductDAO();
		isQuantityIncreased = pDao.increaseQoh(prodID, quantity);
		return isQuantityIncreased;
	}

	
}
