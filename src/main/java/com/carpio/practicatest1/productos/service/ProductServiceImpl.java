package com.carpio.practicatest1.productos.service;

import com.carpio.practicatest1.productos.model.Product;
import com.carpio.practicatest1.productos.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {
    private final IProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id)
        );
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Integer deleteById(Long id) {
        this.productRepository.deleteById(id);
        return id.intValue();
    }

    @Override
    public List<Product> findByCategory(String category) {
        if(category.isBlank()) {
            throw new RuntimeException("Category cannot be blank");
        }
        return this.productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByNameLike(String name) {
        if(name.isBlank()) {
            throw new RuntimeException("Name cannot be blank");
        }
        if(name.contains("~!@#$%^&*()_+-=")) {
            throw new RuntimeException("Name contains invalid characters");
        }
        return this.productRepository.findByNameLike(name);
    }
}
