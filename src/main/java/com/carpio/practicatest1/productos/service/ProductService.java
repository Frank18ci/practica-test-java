package com.carpio.practicatest1.productos.service;

import com.carpio.practicatest1.productos.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Integer deleteById(Long id);
    List<Product> findByCategory(String category);
    List<Product> findByNameLike(String name);
}
