package com.carpio.practicatest1.productos.service;

import com.carpio.practicatest1.productos.model.Product;
import com.carpio.practicatest1.productos.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findAllProductsSuccessfullyTest() {
        List<Product> products = List.of(
                Product.builder().id(1L).name("Laptop").price(1206.0).quantity(5).category("Computer").build(),
                Product.builder().id(2L).name("Mouse").price(20.0).quantity(15).category("Accessories").build(),
                Product.builder().id(3L).name("Keyboard").price(45.0).quantity(10).category("Accessories").build(),
                Product.builder().id(4L).name("PC").price(5500.0).quantity(20).category("Computer").build()
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertAll(
                () -> assertEquals(products.size(), result.size()),
                () -> assertEquals(products.getFirst().getName(), result.getFirst().getName()),
                () -> assertEquals(products.get(1).getName(), result.get(1).getName()),
                () -> assertEquals(products.get(2).getName(), result.get(2).getName()),
                () -> assertEquals(products.get(3).getName(), result.get(3).getName())
        );
    }
    @Test
    void findByIdProductErrorTest() {
        Long id = 99L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.findById(id));

        assertEquals("Product not found with id: " + id, exception.getMessage());

        verify(productRepository).findById(id);
    }
}
