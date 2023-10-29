package br.com.pcvj.integration;

import br.com.pcvj.dto.ProductResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Tag("Integration")
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${api.address.test}")
    private String apiAddressTest;

    @Test
    public void testGetAllProducts() {
        ResponseEntity<List<ProductResponse>> responseEntity = restTemplate.exchange(
                apiAddressTest + port + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponse>>() {
                }
        );
        List<ProductResponse> listProductResponse = responseEntity.getBody();
        assertThat(listProductResponse).isNotNull();
        assertThat(listProductResponse.size()).isEqualTo(30);
    }

    @Test
    public void testGetProductById() {
        ResponseEntity<ProductResponse> responseEntity = restTemplate.exchange(
                apiAddressTest + port + "/products/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ProductResponse>() {
                }
        );
        ProductResponse productResponse = responseEntity.getBody();
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.id()).isEqualTo(1);
        assertThat(productResponse.title()).isEqualTo("iPhone 9");
        assertThat(productResponse.description()).isEqualTo("An apple mobile which is nothing like apple");
        assertThat(productResponse.price()).isEqualTo(549);
        assertThat(productResponse.discountPercentage()).isEqualTo(12.96);
        assertThat(productResponse.rating()).isEqualTo(4.69);
        assertThat(productResponse.stock()).isEqualTo(94);
        assertThat(productResponse.brand()).isEqualTo("Apple");
        assertThat(productResponse.category()).isEqualTo("smartphones");
        assertThat(productResponse.thumbnail()).isEqualTo("https://i.dummyjson.com/data/products/1/thumbnail.jpg");
    }

    @Test
    public void testSearchProducts() {
        ResponseEntity<List<ProductResponse>> responseEntity = restTemplate.exchange(
                apiAddressTest + port + "/products/search?name=iphone",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponse>>() {
                }
        );
        List<ProductResponse> listProductResponse = responseEntity.getBody();
        assertThat(listProductResponse).isNotNull();
        assertThat(listProductResponse.size()).isEqualTo(2);
    }
}
