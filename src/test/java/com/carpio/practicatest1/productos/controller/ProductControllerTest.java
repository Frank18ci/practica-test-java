package com.carpio.practicatest1.productos.controller;

import com.carpio.practicatest1.productos.model.Product;
import com.carpio.practicatest1.productos.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void ReturnAllProductsSuccessfullyTest() throws Exception {
        List<Product> mockProducts = List.of(
                Product.builder().id(1L).name("Laptop").price(1206.0).quantity(5).category("Computer").build(),
                Product.builder().id(2L).name("Mouse").price(20.0).quantity(15).category("Accessories").build(),
                Product.builder().id(3L).name("Keyboard").price(45.0).quantity(10).category("Accessories").build(),
                Product.builder().id(4L).name("PC").price(5500.0).quantity(20).category("Computer").build()
        );

        when(productService.findAll()).thenReturn(mockProducts);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockProducts.size()))
                .andExpect(jsonPath("$[0].id").value(mockProducts.getFirst().getId()))
                .andExpect(jsonPath("$[0].name").value(mockProducts.getFirst().getName()))
                .andExpect(jsonPath("$[0].price").value(mockProducts.getFirst().getPrice()))
                .andExpect(jsonPath("$[0].quantity").value(mockProducts.getFirst().getQuantity()))
                .andExpect(jsonPath("$[0].category").value(mockProducts.getFirst().getCategory()));
    }
}
