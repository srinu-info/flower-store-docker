package com.web.store.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.store.cart.dto.AddToCartRequest;
import com.web.store.cart.dto.CartResponse;
import com.web.store.cart.dto.UpdateCartRequest;
import com.web.store.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @PostMapping("/add")
    public String addToCart(
            Authentication authentication,
            @RequestBody AddToCartRequest request) {

        return cartService.addToCart(
                authentication.getName(),
                request);
    }
    @GetMapping
    public CartResponse getCart(
            Authentication authentication) {

        return cartService.getCart(
                authentication.getName());
    }
    @PutMapping("/update")
    public String updateQuantity(
            @RequestBody UpdateCartRequest request) {

        return cartService.updateQuantity(request);
    }
    @DeleteMapping("/remove/{id}")
    public String removeItem(
            @PathVariable("id") Long id) {

        return cartService.removeItem(id);
    }
    @DeleteMapping("/clear")
    public String clearCart(
            Authentication authentication) {

        return cartService.clearCart(
                authentication.getName());
    }
}
