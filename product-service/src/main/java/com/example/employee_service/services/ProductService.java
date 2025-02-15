package com.example.product_service.services;

import com.example.product_service.models.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        Product createProduct = new Product();
        createProduct.setName(product.getName());
        createProduct.setPrice(product.getPrice());
        return productRepository.save(createProduct);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
