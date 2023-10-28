package br.com.pcvj.mock;

import br.com.pcvj.dto.ProductResponse;
import br.com.pcvj.dto.ProductResponseList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProductFactoryMock {

    private final ObjectMapper objectMapper;

    public ProductFactoryMock() {
        this.objectMapper = new ObjectMapper();
    }

    public List<ProductResponse> createListProductResponse() {
        return List.of(
                createProductResponse());
    }

    public ProductResponse createProductResponse() {
        return new ProductResponse(
                1,
                "Product 1",
                "Product Test",
                10.0,
                10.0,
                10.0,
                10,
                "Product Brand",
                "Product Category",
                "Product Thumb",
                List.of("Product Image 1", "Product Image 2"));
    }

    public String createProductResponseListJson(List<ProductResponse> productListMock) throws JsonProcessingException {
        return objectMapper.writeValueAsString(productListMock);
    }

    public String createProductResponseListJsonWithProperty(List<ProductResponse> productListMock) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new ProductResponseList(productListMock));
    }

    public String createProductResponseJson(ProductResponse productMock) throws JsonProcessingException {
        return objectMapper.writeValueAsString(productMock);
    }
}
