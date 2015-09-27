package com.online.lakeshoremarket.domain;

import com.online.lakeshoremarket.dao.PartnerDAO;
import com.online.lakeshoremarket.model.customer.Address;
import com.online.lakeshoremarket.model.partner.Partner;

public class PartnerDomain {

	PartnerDAO partnerDao = null;
	
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
	
	public int addAddress(Address address){
		int addressID = 0;
		partnerDao = new PartnerDAO();
		addressID = partnerDao.addAddress(address);
		return addressID;
	}

	public boolean deletePartner(int partnerID) {
		boolean isPartnerDeleted = false ;
		partnerDao = new PartnerDAO();
		isPartnerDeleted = partnerDao.deletePartner(partnerID);
		return isPartnerDeleted;
	}
}
