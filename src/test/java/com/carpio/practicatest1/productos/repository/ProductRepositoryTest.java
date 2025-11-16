package com.carpio.practicatest1.productos.repository;

import com.carpio.practicatest1.productos.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//Refactor this method with the appropriate assertions to verify that the ProductRepository methods behave as expected when the repository is empty.
public class ProductRepositoryTest {
    private final IProductRepository productRepository = new ProductRepository();

    @Test
    void findAllReturnsEmptyListWhenRepositoryIsEmpty() {
        List<Product> result = productRepository.findAll();
        assertAll(
                () -> assertEquals(0, result.size())
        );
    }

    @Test
    void findByIdReturnsEmptyOptionalForNonExistingId() {
        var result = productRepository.findById(999L);
        assertEquals(java.util.Optional.empty(), result);
    }

    @Test
    void saveReturnsNullWhenCalled() {
        Product p = Product.builder().id(5L).name("X").price(1.0).quantity(1).category("C").build();
        Product saved = productRepository.save(p);
        assertNull(saved);
    }

    @Test
    void deleteByIdDoesNotThrow() {
        productRepository.deleteById(123L);
    }

    @Test
    void findByCategoryReturnsEmptyList() {
        List<Product> result = productRepository.findByCategory("Accessories");
        assertEquals(0, result.size());
    }

    @Test
    void findByNameLikeReturnsEmptyListForNullAndNonEmpty() {
        List<Product> r1 = productRepository.findByNameLike("Lap");
        List<Product> r2 = productRepository.findByNameLike(null);
        assertAll(
                () -> assertEquals(0, r1.size()),
                () -> assertEquals(0, r2.size())
        );
    }
}
