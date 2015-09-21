package com.online.lakeshoremarket.domain;

import java.util.ArrayList;

import com.online.lakeshoremarket.dao.ProductDAO;
import com.online.lakeshoremarket.model.product.Product;

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

	public Boolean checkProductAvailability(String prodName) {
		Boolean isProductAvailable = false;
		pDao = new ProductDAO();
		isProductAvailable = pDao.getProductAvailability(prodName);
		return isProductAvailable;
	}
	
	public Boolean checkProductAvailabilityByID(int prodID) {
		Boolean isProductAvailable = false;
		pDao = new ProductDAO();
		isProductAvailable = pDao.getProductAvailabilityByID(prodID);
		return isProductAvailable;
	}

	
}
