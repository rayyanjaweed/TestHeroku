package com.online.lakeshoremarket.service;

import com.online.lakeshoremarket.domain.ProductDomain;

public class TestCheckAvailabilityOfProduct {

	public static void main(String[] args) {

		Boolean isProductAvailable = false;
		String prodName = "PS4 Controller";
		ProductDomain prodDomain = new ProductDomain();
		isProductAvailable = prodDomain.checkProductAvailability(prodName);
		System.out.println(isProductAvailable ? "Product is available" : "Product is unavailable");
	}

}
