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

    @Test
    void findByCategorySuccessfullyTest() {
        String category = "Computer";
        List<Product> products = List.of(
                Product.builder().id(1L).name("Laptop").price(1206.0).quantity(5).category("Computer").build(),
                Product.builder().id(4L).name("PC").price(5500.0).quantity(20).category("Computer").build()
        );

        when(productRepository.findByCategory(category)).thenReturn(products);

        List<Product> result = productService.findByCategory(category);

        assertAll(
                () -> assertEquals(products.size(), result.size()),
                () -> assertEquals(products.getFirst().getName(), result.getFirst().getName()),
                () -> assertEquals(products.get(1).getName(), result.get(1).getName())
        );
    }

    @Test
    void findByNameLikeSuccessfullyTest() {
        String name = "top";
        List<Product> products = List.of(
                Product.builder().id(1L).name("Laptop").price(1206.0).quantity(5).category("Computer").build(),
                Product.builder().id(5L).name("Desktop").price(2500.0).quantity(8).category("Computer").build()
        );

        when(productRepository.findByNameLike(name)).thenReturn(products);

        List<Product> result = productService.findByNameLike(name);

        assertAll(
                () -> assertEquals(products.size(), result.size()),
                () -> assertEquals(products.getFirst().getName(), result.getFirst().getName())
        );
    }

    @Test
    void deleteByIdSuccessfullyTest() {
        Long id = 2L;

        Integer result = productService.deleteById(id);

        assertEquals(id.intValue(), result);

        verify(productRepository).deleteById(id);
    }

    @Test
    void saveProductSuccessfullyTest() {
        Product productToSave = Product.builder()
                .name("Monitor")
                .price(300.0)
                .quantity(12)
                .category("Accessories")
                .build();

        Product savedProduct = Product.builder()
                .id(6L)
                .name("Monitor")
                .price(300.0)
                .quantity(12)
                .category("Accessories")
                .build();

        when(productRepository.save(productToSave)).thenReturn(savedProduct);

        Product result = productService.save(productToSave);

        assertAll(
                () -> assertNotNull(result.getId()),
                () -> assertEquals(savedProduct.getName(), result.getName()),
                () -> assertEquals(savedProduct.getPrice(), result.getPrice()),
                () -> assertEquals(savedProduct.getQuantity(), result.getQuantity()),
                () -> assertEquals(savedProduct.getCategory(), result.getCategory())
        );
    }

    @Test
    void findByCategoryErrorCategoryBlankTest() {
        String category = "   ";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.findByCategory(category));

        assertEquals("Category cannot be blank", exception.getMessage());
    }

    @Test
    void findByNameLikeErrorNameBlankTest() {
        String name = "   ";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.findByNameLike(name));

        assertEquals("Name cannot be blank", exception.getMessage());
    }

    @Test
    void findByNameLikeErrorNameInvalidCharactersTest() {
        String name = "Laptop~!@#()";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.findByNameLike(name));
        assertEquals("Name contains invalid characters", exception.getMessage());
    }
}
