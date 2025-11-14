package com.carpio.practicatest1.productos.controller;

import com.carpio.practicatest1.productos.model.Product;
import com.carpio.practicatest1.productos.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static com.carpio.practicatest1.utils.TestExpectations.expectAllListOfClassAt;
import static com.carpio.practicatest1.utils.TestUtils.getResource;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private static final String LIST_SUCCESSFULLY_JSON = "list_successfully.json";

    @Test
    void returnAllProductsSuccessfully() throws Exception {
        final Product[] mockProductsArray = getResource(LIST_SUCCESSFULLY_JSON, Product[].class, this.getClass());

        final List<Product> mockProducts = Arrays.asList(mockProductsArray);

        when(productService.findAll()).thenReturn(mockProducts);

        ResultActions actions = mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(mockProducts.size()));
        expectAllListOfClassAt(actions, mockProducts);
    }

    @Test
    void returnProductByIdSuccessfully() throws Exception {
        final Long productId = 1L;
        final Product mockProduct = getResource("product_by_id_successfully.json", Product.class, this.getClass());

        when(productService.findById(productId)).thenReturn(mockProduct);

        mockMvc.perform(get("/api/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockProduct.getId()))
                .andExpect(jsonPath("$.name").value(mockProduct.getName()))
                .andExpect(jsonPath("$.price").value(mockProduct.getPrice()))
                .andExpect(jsonPath("$.quantity").value(mockProduct.getQuantity()))
                .andExpect(jsonPath("$.category").value(mockProduct.getCategory()));
    }

    @Test
    void returnProductByIdNotFound() throws Exception {
        final Long productId = 99L;

        when(productService.findById(productId)).thenThrow(new RuntimeException("Product not found with id: " + productId));

        mockMvc.perform(get("/api/products/{id}", productId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Product not found with id: " + productId));
    }

    @Test
    void returnSaveProductSuccessfully() throws Exception {
        final Product mockProduct = getResource("product_save_successfully.json", Product.class, this.getClass());

        when(productService.save(mockProduct)).thenReturn(mockProduct);

        mockMvc.perform(post("/api/products")
                .contentType("application/json")
                .content("{\"name\":\"Tablet\",\"price\":300.0,\"quantity\":8,\"category\":\"Electronics\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockProduct.getId()))
                .andExpect(jsonPath("$.name").value(mockProduct.getName()))
                .andExpect(jsonPath("$.price").value(mockProduct.getPrice()))
                .andExpect(jsonPath("$.quantity").value(mockProduct.getQuantity()))
                .andExpect(jsonPath("$.category").value(mockProduct.getCategory()));
    }

}
