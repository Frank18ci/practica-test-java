package com.carpio.practicatest1.productos.repository;

import com.carpio.practicatest1.productos.model.Product;

import java.util.List;
import java.util.Optional;


public interface IProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    List<Product> findByCategory(String category);
    List<Product> findByNameLike(String name);
}
