package com.carpio.practicatest1.productos.controller;

import com.carpio.practicatest1.productos.model.Product;
import com.carpio.practicatest1.productos.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }
}
