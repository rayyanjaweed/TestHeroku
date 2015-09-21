package com.online.lakeshoremarket.service;

import com.online.lakeshoremarket.domain.CustomerDomain;

public class TestDeleteCustomer {

	public static void main(String[] args) {

		CustomerDomain custDomain = new CustomerDomain();
		int custID = 1;
		custDomain.deleteCustomer(custID);
	}

}
