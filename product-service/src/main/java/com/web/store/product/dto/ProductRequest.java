package com.web.store.product.dto;

import java.util.*;

import com.web.store.product.entity.Category;

public class ProductRequest {

    private String name;

    private String description;

    private String imageUrl;

    private boolean bestSelling;

    private boolean newArrival;

    private Set<Category> categories;

    private List<ProductSizeDto> sizes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isBestSelling() {
		return bestSelling;
	}

	public void setBestSelling(boolean bestSelling) {
		this.bestSelling = bestSelling;
	}

	public boolean isNewArrival() {
		return newArrival;
	}

	public void setNewArrival(boolean newArrival) {
		this.newArrival = newArrival;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public List<ProductSizeDto> getSizes() {
		return sizes;
	}

	public void setSizes(List<ProductSizeDto> sizes) {
		this.sizes = sizes;
	}
    

}