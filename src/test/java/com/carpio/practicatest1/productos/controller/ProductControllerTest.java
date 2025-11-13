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

}
