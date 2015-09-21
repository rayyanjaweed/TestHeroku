package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.PaymentDAO;
import com.online.lakeshoremarket.dao.ProductDAO;

public class PaymentDomain {

	PaymentDAO pDao = null;
	public int buyProduct(int prodID) {
		Boolean isProductAvailable = false;
		ProductDomain prodDomain = new ProductDomain();
		int orderID = 0;
		isProductAvailable = prodDomain.checkProductAvailabilityByID(prodID);
		if(isProductAvailable){
			pDao = new PaymentDAO();
//			acceptPayment(Payment custPayment);
		}
		return orderID;
	}
	
	/*public int acceptPayment(Payment custPayment){
		
	}*/
}
