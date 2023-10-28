package br.com.pcvj.rest;

import br.com.pcvj.dto.ProductResponse;
import br.com.pcvj.dto.ProductResponseList;
import br.com.pcvj.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient(@Value("${api.url}") String apiUrl, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(apiUrl).setConnectTimeout(Duration.ofSeconds(2)).build();
    }

    public List<ProductResponse> getAllProducts() {
        return Objects.requireNonNull(
                        this.restTemplate.getForObject("/products", ProductResponseList.class))
                .products();
    }

    public ProductResponse getProductById(int id) {
        try {
            return this.restTemplate.getForEntity("/products/" + id, ProductResponse.class).getBody();
        } catch (RestClientException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public List<ProductResponse> searchProducts(String searchParam) {
        return Objects.requireNonNull(
                        this.restTemplate.getForObject("/products/search?q=" + searchParam, ProductResponseList.class))
                .products();
    }
}