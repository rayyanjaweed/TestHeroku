package com.online.lakeshoremarket.model.partner;

public interface Partner {

	public boolean isActive();
	public void setActive(boolean isActive);
	public int getAddressID();
	public void setAddressID(int addressID);
	public int getPartnerID();
	public void setPartnerID(int partnerID);
	public String getContactName();
	public void setContactName(String contactName);
	public String getEmail();
	public void setEmail(String email);
	public String getPartnerName();
	public void setPartnerName(String partnerName);
	public String getPassword();
	public void setPassword(String password);
	public String getPhone();
	public void setPhone(String phone);
	
	
}
