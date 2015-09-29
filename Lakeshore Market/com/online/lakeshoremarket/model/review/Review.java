package com.online.lakeshoremarket.model.review;

import java.sql.Timestamp;
/**
 * represents a review in the database
 *
 */
public interface Review {

	public int getCustomerID();
	public void setCustomerID(int customerID);
	public int getPartnerID();
	public void setPartnerID(int partnerID);
	public int getPartnerReviewID();
	public void setPartnerReviewID(int partnerReviewID);
	public int getProductID();
	public void setProductID(int productID);
	public int getProductReviewID();
	public void setProductReviewID(int productReviewID);
	public int getRating();
	public void setRating(int rating);
	public String getReview();
	public void setReview(String review);
	public Timestamp getReviewDate();
	public void setReviewDate(Timestamp reviewDate);
	
	
}
