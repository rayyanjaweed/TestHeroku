package com.online.lakeshoremarket.service;

import com.online.lakeshoremarket.domain.PaymentDomain;

public class TestBuyProduct {

	public static void main(String[] args) {

		PaymentDomain paymentDomain = new PaymentDomain();
		int prodID = 15;
		int quantity = 3;
		int custID = 1;
		paymentDomain.buyProduct(prodID, quantity, custID);
	}

}
