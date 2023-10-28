package br.com.pcvj.dto;

import java.util.List;

public record ProductResponseList(
        List<ProductResponse> products
) {
}
