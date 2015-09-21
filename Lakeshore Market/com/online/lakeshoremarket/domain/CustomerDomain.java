package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.CustomerDAO;
import com.online.lakeshoremarket.model.customer.Address;
import com.online.lakeshoremarket.model.customer.Customer;

public class CustomerDomain {

	CustomerDAO custDao = null;
	
	public int addCustomer(Customer cust, Address billingAddress, Address shippingAddress) {
		int rowsUpdated = 0;
		int billingAddressID = addAddress(billingAddress);
		int shippingAddressID = addAddress(shippingAddress);
		if(0 != billingAddressID && 0 != shippingAddressID){
			cust.setBillingAddress(billingAddressID);
			cust.setShippingAddress(shippingAddressID);
			custDao = new CustomerDAO();
			rowsUpdated = custDao.addCustomer(cust);
			
		}
		return rowsUpdated;
	}
	
	public int addAddress(Address address){
		int addressID = 0;
		custDao = new CustomerDAO();
		addressID = custDao.addAddress(address);
		return addressID;
	}

	public void deleteCustomer(int custID) {
		custDao = new CustomerDAO();
		custDao.deleteCustomerAddress();
		custDao.deleteCustomer(custID);
	}

}
