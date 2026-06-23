package com.web.store.product.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.store.product.dto.ProductRequest;
import com.web.store.product.dto.ProductResponse;
import com.web.store.product.dto.ProductSizeDto;
import com.web.store.product.entity.Category;
import com.web.store.product.entity.Product;
import com.web.store.product.entity.ProductSize;
import com.web.store.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setBestSelling(request.isBestSelling());
        product.setNewArrival(request.isNewArrival());
        product.setCategories(request.getCategories());

        List<ProductSize> sizeList = new ArrayList<>();

        for (ProductSizeDto dto : request.getSizes()) {

            ProductSize size = new ProductSize();

            size.setSize(dto.getSize());
            size.setPrice(dto.getPrice());
            size.setProduct(product);

            sizeList.add(size);
        }

        product.setSizes(sizeList);

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        return mapToResponse(product);
    }

    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }
    public List<ProductResponse> bestSellingProducts() {

        return productRepository
                .findByBestSellingTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public List<ProductResponse> newArrivalProducts() {

        return productRepository
                .findByNewArrivalTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public ProductResponse updateProduct(
            Long id,
            ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setBestSelling(request.isBestSelling());
        product.setNewArrival(request.isNewArrival());
        product.setCategories(request.getCategories());

        product.getSizes().clear();

        List<ProductSize> sizes = new ArrayList<>();

        for(ProductSizeDto dto : request.getSizes()) {

            ProductSize size = new ProductSize();

            size.setSize(dto.getSize());
            size.setPrice(dto.getPrice());
            size.setProduct(product);

            sizes.add(size);
        }

        product.getSizes().addAll(sizes);

        return mapToResponse(
                productRepository.save(product));
    }
    public List<ProductResponse> searchProducts(
            String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public List<ProductResponse> getProductsByCategory(
            Category category) {

        return productRepository
                .findByCategoriesContaining(category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public List<ProductResponse> sortByPriceAsc() {

        return productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(
                        p -> p.getSizes()
                              .stream()
                              .map(ProductSize::getPrice)
                              .min(Double::compareTo)
                              .orElse(0.0)))
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setImageUrl(product.getImageUrl());
        response.setBestSelling(product.isBestSelling());
        response.setNewArrival(product.isNewArrival());
        response.setCategories(product.getCategories());

        List<ProductSizeDto> sizeDtos =
                product.getSizes()
                        .stream()
                        .map(size -> {

                            ProductSizeDto dto =
                                    new ProductSizeDto();

                            dto.setSize(size.getSize());
                            dto.setPrice(size.getPrice());

                            return dto;
                        })
                        .toList();

        response.setSizes(sizeDtos);

        return response;
    }
}