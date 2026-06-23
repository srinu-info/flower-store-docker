package com.web.store.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.store.product.dto.ProductRequest;
import com.web.store.product.dto.ProductResponse;
import com.web.store.product.entity.Category;
import com.web.store.product.service.ProductService;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/products")
    public ProductResponse createProduct(
            @RequestBody ProductRequest request) {

        return productService.createProduct(request);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(
            @PathVariable("id") Long id) {

        return productService.getProductById(id);
    }

    @DeleteMapping("/admin/products/{id}")
    public String deleteProduct(
            @PathVariable("id") Long id) {

        return productService.deleteProduct(id);
    }
    @PutMapping("/admin/products/{id}")
    public ProductResponse updateProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }
    @GetMapping("/products/search")
    public List<ProductResponse> searchProducts(
            @RequestParam String keyword) {

        return productService.searchProducts(keyword);
    }
    @GetMapping("/products/category/{category}")
    public List<ProductResponse> getByCategory(
            @PathVariable Category category) {

        return productService
                .getProductsByCategory(category);
    }
    @GetMapping("/products/best-selling")
    public List<ProductResponse> bestSellingProducts() {

        return productService.bestSellingProducts();
    }
    @GetMapping("/products/new-arrivals")
    public List<ProductResponse> newArrivalProducts() {

        return productService.newArrivalProducts();
    }
    @GetMapping("/products/sort/price")
    public List<ProductResponse> sortByPrice() {

        return productService.sortByPriceAsc();
    }
}
