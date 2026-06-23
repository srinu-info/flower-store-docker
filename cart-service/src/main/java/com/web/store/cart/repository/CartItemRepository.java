package com.web.store.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.cart.entity.CartItem;

@Repository
public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {
}