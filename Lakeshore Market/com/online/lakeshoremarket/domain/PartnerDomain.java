package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.CustomerDAO;
import com.online.lakeshoremarket.dao.PartnerDAO;
import com.online.lakeshoremarket.model.customer.Address;
import com.online.lakeshoremarket.model.partner.Partner;

/**
 * Represents the partner domain business logic
 *
 */
public class PartnerDomain {

	PartnerDAO partnerDao = null;
	
	/**
	 * adds a partner to the db
	 * @param partner 	the partner to add
	 * @param address	the address to set for the partner
	 * @return			number of rows updated
	 */
	public int addPartner(Partner partner, Address address) {
		int rowsUpdated = 0;
		int addressID = addAddress(address);
		if(0 != addressID){
			partner.setAddressID(addressID);
			partnerDao = new PartnerDAO();
			rowsUpdated = partnerDao.addPartner(partner);
			
		}
		return rowsUpdated;
	}
	
	/**
	 * adds an address to the database
	 * @param address	the address to insert
	 * @return			address ID or 0 on failure
	 */
	public int addAddress(Address address){
		int addressID = 0;
		partnerDao = new PartnerDAO();
		addressID = partnerDao.addAddress(address);
		return addressID;
	}

	/**
	 * marks a partner as inactive
	 * @param partnerID 	the partner ID to update
	 * @return				true on success, else false
	 */
	public boolean deletePartner(int partnerID) {
		boolean isPartnerDeleted = false ;
		partnerDao = new PartnerDAO();
		isPartnerDeleted = partnerDao.deletePartner(partnerID);
		return isPartnerDeleted;
	}

	/**
	 * determines if a partner is active or not
	 * @param partnerID 	the partner to look up
	 * @return				true if active, else false
	 */
	public boolean getStatus(int partnerID) {
		boolean isPartnerActive = false;
		partnerDao = new PartnerDAO();
		isPartnerActive = partnerDao.getStatus(partnerID);
		return isPartnerActive;
	}
}
