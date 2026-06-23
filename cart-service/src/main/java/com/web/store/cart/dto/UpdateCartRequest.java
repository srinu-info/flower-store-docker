package com.web.store.cart.dto;

import jakarta.validation.constraints.*;

public class UpdateCartRequest {

    @NotNull
    private Long cartItemId;

    @NotNull
    @Min(1)
    private Integer quantity;

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

   
}