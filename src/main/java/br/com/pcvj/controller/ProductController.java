package br.com.pcvj.controller;

import br.com.pcvj.dto.ProductResponse;
import br.com.pcvj.rest.ProductClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductClient productClient;

    @Autowired
    public ProductController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Operation(summary = "Get a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of products",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))})})
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productClient.getAllProducts());
    }

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productClient.getProductById(id));
    }

    @Operation(summary = "Get a list of products accordingly to informed parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of products",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Parameter not informed",
                    content = @Content)})
    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam String name) {
        return productClient.searchProducts(name);
    }
}