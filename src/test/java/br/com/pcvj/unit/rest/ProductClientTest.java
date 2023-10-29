package br.com.pcvj.unit.rest;

import br.com.pcvj.dto.ProductResponse;
import br.com.pcvj.exception.ResourceNotFoundException;
import br.com.pcvj.rest.ProductClient;
import br.com.pcvj.unit.mock.ProductFactoryMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RestClientTest(ProductClient.class)
@Tag("Unit")
class ProductClientTest {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    private ProductFactoryMock productFactoryMock;

    @BeforeEach
    public void setup() {
        productFactoryMock = new ProductFactoryMock();
    }

    @Test
    void getAllProducts() throws JsonProcessingException {
        List<ProductResponse> productListMock = productFactoryMock.createListProductResponse();
        String productResponseListJson = productFactoryMock.createProductResponseListJsonWithProperty(productListMock);
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/products"))
                .andRespond(MockRestResponseCreators.withSuccess(productResponseListJson, MediaType.APPLICATION_JSON));

        var response = productClient.getAllProducts();

        assertEquals(productListMock, response);
    }

    @Test
    void getProductById() throws JsonProcessingException {
        ProductResponse productMock = productFactoryMock.createProductResponse();
        String productResponseJson = productFactoryMock.createProductResponseJson(productMock);
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/products/1"))
                .andRespond(MockRestResponseCreators.withSuccess(productResponseJson, MediaType.APPLICATION_JSON));

        var response = productClient.getProductById(1);

        assertEquals(productMock, response);
    }

    @Test
    void getProductByIdNotFound() {
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/products/1"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        assertThrows(ResourceNotFoundException.class, () -> productClient.getProductById(1));
    }

    @Test
    void searchProduct() throws JsonProcessingException {
        List<ProductResponse> productListMock = productFactoryMock.createListProductResponse();
        String productResponseListJson = productFactoryMock.createProductResponseListJsonWithProperty(productListMock);
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/products/search?q=product"))
                .andRespond(MockRestResponseCreators.withSuccess(productResponseListJson, MediaType.APPLICATION_JSON));

        var response = productClient.searchProducts("product");

        assertEquals(productListMock, response);
    }

    @Test
    void searchProductNotFound() throws JsonProcessingException {
        List<ProductResponse> emptyList = List.of();
        String productResponseListEmptyJson = productFactoryMock.createProductResponseListJsonWithProperty(emptyList);
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/products/search?q=test"))
                .andRespond(MockRestResponseCreators.withSuccess(productResponseListEmptyJson, MediaType.APPLICATION_JSON));

        var response = productClient.searchProducts("test");

        assertEquals(emptyList, response);
    }
}