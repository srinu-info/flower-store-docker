package com.web.store.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.cart.entity.Cart;

@Repository
public interface CartRepository
        extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserEmail(String email);
}