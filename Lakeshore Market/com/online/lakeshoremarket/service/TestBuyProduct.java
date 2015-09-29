package com.online.lakeshoremarket.service;

import com.online.lakeshoremarket.domain.PaymentDomain;

public class TestBuyProduct {

	public static void main(String[] args) {

		PaymentDomain paymentDomain = new PaymentDomain();
		int prodID = 13;
		int quantity = 4;
		int custID = 1;
		paymentDomain.buyProduct(prodID, quantity, custID);
	}

}
