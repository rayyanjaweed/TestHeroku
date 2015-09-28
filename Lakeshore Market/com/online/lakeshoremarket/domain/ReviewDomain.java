package com.online.lakeshoremarket.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.online.lakeshoremarket.dao.ReviewDAO;
import com.online.lakeshoremarket.model.review.Review;

public class ReviewDomain {
	
	ReviewDAO reviewDAO = null;

	public boolean addPartnerReview(Review review) {
		boolean isPartnerReviewAdded = false;
		reviewDAO = new ReviewDAO();
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());
		review.setReviewDate(date);
		isPartnerReviewAdded = reviewDAO.addPartnerReview(review);
		return isPartnerReviewAdded;
	}

	public boolean addProductReview(Review review) {
		boolean isProductReviewAdded = false;
		reviewDAO = new ReviewDAO();
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());
		review.setReviewDate(date);
		isProductReviewAdded = reviewDAO.addProductReview(review);
		return isProductReviewAdded;
	}

}
