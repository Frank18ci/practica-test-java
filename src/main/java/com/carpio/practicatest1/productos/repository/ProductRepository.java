package com.carpio.practicatest1.productos.repository;

import com.carpio.practicatest1.productos.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductRepository implements IProductRepository{
    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Product> findByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return List.of();
    }
}
