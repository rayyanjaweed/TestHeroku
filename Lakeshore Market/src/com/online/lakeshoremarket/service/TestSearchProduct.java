package com.online.lakeshoremarket.service;

import com.online.lakeshoremarket.domain.ProductDomain;
import com.online.lakeshoremarket.model.product.Product;

public class TestSearchProduct {

	public static void main(String[] args) {

		Product prod = null;
		String prodName = "PS4 Controller";
		ProductDomain prodDomain = new ProductDomain();
		prod = prodDomain.searchProductByName(prodName);
		prod.displayProductDetails();
	}

}
