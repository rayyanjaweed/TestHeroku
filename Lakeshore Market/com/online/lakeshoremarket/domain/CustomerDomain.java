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

	public boolean deleteCustomer(int custID) {
		boolean isCustomerDeleted = false ;
		custDao = new CustomerDAO();
		isCustomerDeleted = custDao.deleteCustomer(custID);
		
		//This code section is commented because we don't actually have to delete the customer instead make the customer inactive
		/*int billingAddressID = 0;
		int shippingAddressID = 0;
		int funcReturn = 0;
		billingAddressID = getCustomerBillingAddress(custID);
		shippingAddressID = getCustomerShippingAddress(custID);
		if(0 != billingAddressID && 0 != shippingAddressID){
			funcReturn = custDao.deleteCustomer(custID);
			if(0 != funcReturn){
				int billingAddressDeleted = 0;
				int shippingAddressDeleted = 0;
				billingAddressDeleted = custDao.deleteCustomerAddress(billingAddressID);
				shippingAddressDeleted = custDao.deleteCustomerAddress(shippingAddressID);
				if(0 != billingAddressDeleted && 0 != shippingAddressDeleted){
					isCustomerDeleted = true;
				}
			}
		}*/
		return isCustomerDeleted;
	}
	
	public void deleteCustomerAddress(int custID){
		custDao = new CustomerDAO();
		custDao.deleteCustomerAddress(custID);
	}
	
	public int getCustomerBillingAddress(int custID){
		int billingAddressID = 0;
		custDao = new CustomerDAO();
		billingAddressID = custDao.getCustomerBillingAddress(custID);
		return billingAddressID;
	}
	
	public int getCustomerShippingAddress(int custID){
		int shippingAddressID = 0;
		custDao = new CustomerDAO();
		shippingAddressID = custDao.getCustomerShippingAddress(custID);
		return shippingAddressID;
	}

	public boolean getStatus(int custID) {
		boolean isCustomerActive = false;
		custDao = new CustomerDAO();
		isCustomerActive = custDao.getStatus(custID);
		return isCustomerActive;
	}

}
