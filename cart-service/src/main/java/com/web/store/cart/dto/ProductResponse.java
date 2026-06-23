package com.web.store.cart.dto;

import java.util.List;

public class ProductResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private List<ProductSizeDto> sizes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ProductSizeDto> getSizes() {
		return sizes;
	}

	public void setSizes(List<ProductSizeDto> sizes) {
		this.sizes = sizes;
	}

  
}
