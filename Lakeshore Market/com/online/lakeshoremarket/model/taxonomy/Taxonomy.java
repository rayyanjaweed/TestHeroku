package com.online.lakeshoremarket.model.taxonomy;

/**
 * represents a taxonomy in the database
 *
 */
public interface Taxonomy {

	public int getTaxonomyID();
	public void setTaxonomyID(int taxonomyID);
	public String getTaxonomyName();
	public void setTaxonomyName(String taxonomyName);
	public String getSlug();
	public void setSlug(String slug);
	
	
}
