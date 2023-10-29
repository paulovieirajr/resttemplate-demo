package br.com.pcvj.unit.controller;

import br.com.pcvj.controller.ProductController;
import br.com.pcvj.dto.ProductResponse;
import br.com.pcvj.exception.ResourceNotFoundException;
import br.com.pcvj.rest.ProductClient;
import br.com.pcvj.unit.mock.ProductFactoryMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@Tag("Unit")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductClient productClient;

    private ProductFactoryMock productFactoryMock;

    @BeforeEach
    public void setup() {
        productFactoryMock = new ProductFactoryMock();
    }

    @Test
    void getAllProducts() throws Exception {
        List<ProductResponse> productListMock = productFactoryMock.createListProductResponse();
        when(productClient.getAllProducts()).thenReturn(productListMock);

        String productResponseListJson = productFactoryMock.createProductResponseListJson(productListMock);
        this.mockMvc
                .perform(get("/products")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(productResponseListJson));
    }

    @Test
    void getProductById() throws Exception {
        ProductResponse productMock = productFactoryMock.createProductResponse();
        when(productClient.getProductById(anyInt())).thenReturn(productMock);

        this.mockMvc
                .perform(get("/products/{id}", 1)
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(productFactoryMock.createProductResponseJson(productMock)));
    }

    @Test
    void getProductByIdNotFound() throws Exception {
        when(productClient.getProductById(anyInt())).thenThrow(ResourceNotFoundException.class);

        this.mockMvc
                .perform(get("/products/{id}", 100000)
                        .accept("application/problem+json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource not found"))
                .andExpect(jsonPath("$.detail").value("The resource you were trying to reach is not found"))
                .andExpect(jsonPath("$.status").value("404"));
    }

    @Test
    void searchProducts() throws Exception {
        List<ProductResponse> productListMock = productFactoryMock.createListProductResponse();
        when(productClient.searchProducts("test")).thenReturn(productListMock);

        String productResponseListJson = productFactoryMock.createProductResponseListJson(productListMock);
        this.mockMvc
                .perform(get("/products/search")
                        .param("name", "test")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(productResponseListJson));
    }

    @Test
    void searchProductsNotFound() throws Exception {
        when(productClient.searchProducts("test")).thenReturn(List.of());

        this.mockMvc
                .perform(get("/products/search")
                        .param("name", "test")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}