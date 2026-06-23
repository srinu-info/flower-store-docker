package com.web.store.cart.dto;

import jakarta.validation.constraints.*;

public class AddToCartRequest {

    @NotNull
    private Long productId;

    @NotBlank
    private String size;

    @NotNull
    @Min(1)
    private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    // getters setters
}
