package com.online.lakeshoremarket.model.product;

public interface Product {

	public boolean isActive();
	public void setActive(boolean isActive);
	public float getCost();
	public void setCost(float cost);
	public float getPrice();
	public void setPrice(float price);
	public int getPartnerID();
	public void setPartnerID(int partnerID);
	public int getProductID();
	public void setProductID(int productID);
	public int getTaxonomyID();
	public void setTaxonomyID(int taxonomyID);
	public int getQoh();
	public void setQoh(int qoh);
	public String getProductName();
	public void setProductName(String productName);
	public String getDescription();
	public void setDescription(String description);
	public void displayProductDetails();
	
	
}
