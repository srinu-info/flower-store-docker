package com.web.store.product.config;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.web.store.product.entity.Category;
import com.web.store.product.entity.Product;
import com.web.store.product.entity.ProductSize;
import com.web.store.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {

        if (productRepository.count() > 0) {
            return;
        }

        saveRoseBouquet();
        saveWhiteLily();
        saveWeddingFlowers();
        saveLoveCombo();
        saveBirthdayCombo();
    }

    private void saveRoseBouquet() {

        Product product = Product.builder()
                .name("Rose Bouquet")
                .description("Beautiful red roses bouquet")
                .imageUrl("https://images.unsplash.com/photo-1519378058457-4c29a0a2efac")
                .bestSelling(true)
                .newArrival(false)
                .categories(Set.of(Category.BIRTHDAY))
                .build();

        ProductSize small = ProductSize.builder()
                .size("SMALL")
                .price(499.0)
                .product(product)
                .build();

        ProductSize medium = ProductSize.builder()
                .size("MEDIUM")
                .price(699.0)
                .product(product)
                .build();

        ProductSize large = ProductSize.builder()
                .size("LARGE")
                .price(899.0)
                .product(product)
                .build();

        product.setSizes(List.of(small, medium, large));

        productRepository.save(product);
    }

    private void saveWhiteLily() {

        Product product = Product.builder()
                .name("White Lily Bouquet")
                .description("Fresh white lily flowers")
                .imageUrl("https://images.unsplash.com/photo-1468327768560-75b778cbb551")
                .bestSelling(true)
                .newArrival(true)
                .categories(Set.of(Category.SYMPATHY))
                .build();

        ProductSize small = ProductSize.builder()
                .size("SMALL")
                .price(599.0)
                .product(product)
                .build();

        ProductSize medium = ProductSize.builder()
                .size("MEDIUM")
                .price(799.0)
                .product(product)
                .build();

        ProductSize large = ProductSize.builder()
                .size("LARGE")
                .price(999.0)
                .product(product)
                .build();

        product.setSizes(List.of(small, medium, large));

        productRepository.save(product);
    }

    private void saveWeddingFlowers() {

        Product product = Product.builder()
                .name("Wedding Flower Basket")
                .description("Premium wedding floral arrangement")
                .imageUrl("https://images.unsplash.com/photo-1526047932273-341f2a7631f9")
                .bestSelling(false)
                .newArrival(true)
                .categories(Set.of(Category.MARRIAGE))
                .build();

        ProductSize small = ProductSize.builder()
                .size("SMALL")
                .price(999.0)
                .product(product)
                .build();

        ProductSize medium = ProductSize.builder()
                .size("MEDIUM")
                .price(1499.0)
                .product(product)
                .build();

        ProductSize large = ProductSize.builder()
                .size("LARGE")
                .price(1999.0)
                .product(product)
                .build();

        product.setSizes(List.of(small, medium, large));

        productRepository.save(product);
    }

    private void saveLoveCombo() {

        Product product = Product.builder()
                .name("Love Roses Combo")
                .description("Special roses for your loved one")
                .imageUrl("https://images.unsplash.com/photo-1518895949257-7621c3c786d7")
                .bestSelling(true)
                .newArrival(false)
                .categories(Set.of(Category.LOVE))
                .build();

        ProductSize small = ProductSize.builder()
                .size("SMALL")
                .price(699.0)
                .product(product)
                .build();

        ProductSize medium = ProductSize.builder()
                .size("MEDIUM")
                .price(999.0)
                .product(product)
                .build();

        ProductSize large = ProductSize.builder()
                .size("LARGE")
                .price(1299.0)
                .product(product)
                .build();

        product.setSizes(List.of(small, medium, large));

        productRepository.save(product);
    }

    private void saveBirthdayCombo() {

        Product product = Product.builder()
                .name("Birthday Celebration Flowers")
                .description("Flowers for birthday celebrations")
                .imageUrl("https://images.unsplash.com/photo-1490750967868-88aa4486c946")
                .bestSelling(false)
                .newArrival(true)
                .categories(Set.of(Category.BIRTHDAY))
                .build();

        ProductSize small = ProductSize.builder()
                .size("SMALL")
                .price(499.0)
                .product(product)
                .build();

        ProductSize medium = ProductSize.builder()
                .size("MEDIUM")
                .price(799.0)
                .product(product)
                .build();

        ProductSize large = ProductSize.builder()
                .size("LARGE")
                .price(1099.0)
                .product(product)
                .build();

        product.setSizes(List.of(small, medium, large));

        productRepository.save(product);
    }
}