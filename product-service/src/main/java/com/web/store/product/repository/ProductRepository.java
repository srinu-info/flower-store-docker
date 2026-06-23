package com.web.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.product.entity.Category;
import com.web.store.product.entity.Product;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByBestSellingTrue();

    List<Product> findByNewArrivalTrue();
    List<Product> findByCategoriesContaining(
            Category category);
}