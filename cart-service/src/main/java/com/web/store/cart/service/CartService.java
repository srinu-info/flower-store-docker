package com.web.store.cart.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.web.store.cart.dto.AddToCartRequest;
import com.web.store.cart.dto.CartItemResponse;
import com.web.store.cart.dto.CartResponse;
import com.web.store.cart.dto.ProductResponse;
import com.web.store.cart.dto.ProductSizeDto;
import com.web.store.cart.dto.UpdateCartRequest;
import com.web.store.cart.entity.Cart;
import com.web.store.cart.entity.CartItem;
import com.web.store.cart.feignClient.ProductClient;
import com.web.store.cart.repository.CartItemRepository;
import com.web.store.cart.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    
    @Autowired
    ProductClient  productClient;

    private static final String PRODUCT_SERVICE =
            "http://localhost:8082/products/";
    
    public String addToCart(
            String email,
            AddToCartRequest request) {

        Cart cart = cartRepository
                .findByUserEmail(email)
                .orElseGet(() -> {

                    Cart newCart = new Cart();
                    newCart.setUserEmail(email);

                    return cartRepository.save(newCart);
                });

        ProductResponse product =
                productClient.getProductById(
                        request.getProductId());

        if(product == null) {
            throw new RuntimeException(
                    "Product not found");
        }

        Double selectedPrice = product.getSizes()
                .stream()
                .filter(size ->
                        size.getSize()
                                .equalsIgnoreCase(
                                        request.getSize()))
                .findFirst()
                .map(ProductSizeDto::getPrice)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Size not found"));

        CartItem item = new CartItem();

        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setImageUrl(product.getImageUrl());
        item.setSize(request.getSize());
        item.setQuantity(request.getQuantity());
        item.setPrice(selectedPrice);
        item.setCart(cart);

        cartItemRepository.save(item);

        return "Product added to cart";
    }
    public CartResponse getCart(String email) {

        Cart cart = cartRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart not found"));

        CartResponse response =
                new CartResponse();

        response.setCartId(cart.getId());
        response.setUserEmail(cart.getUserEmail());

        List<CartItemResponse> items =
                cart.getItems()
                        .stream()
                        .map(item -> {

                            CartItemResponse dto =
                                    new CartItemResponse();

                            dto.setId(item.getId());
                            dto.setProductId(item.getProductId());
                            dto.setProductName(item.getProductName());
                            dto.setImageUrl(item.getImageUrl());
                            dto.setSize(item.getSize());
                            dto.setQuantity(item.getQuantity());
                            dto.setPrice(item.getPrice());

                            dto.setSubtotal(
                                    item.getPrice()
                                    * item.getQuantity());

                            return dto;
                        }).toList();

        response.setItems(items);

        double total = items.stream()
                .mapToDouble(
                        CartItemResponse::getSubtotal)
                .sum();

        response.setTotalAmount(total);

        return response;
    }
    public String updateQuantity(
            UpdateCartRequest request) {

        CartItem item =
                cartItemRepository
                        .findById(
                                request.getCartItemId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"));

        item.setQuantity(
                request.getQuantity());

        cartItemRepository.save(item);

        return "Quantity updated";
    }
    public String removeItem(
            Long cartItemId) {

        CartItem item =
                cartItemRepository
                        .findById(cartItemId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"));

        cartItemRepository.delete(item);

        return "Item removed";
    }
    public String clearCart(
            String email) {

        Cart cart =
                cartRepository
                        .findByUserEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart not found"));

        cart.getItems().clear();

        cartRepository.save(cart);

        return "Cart cleared";
    }
}