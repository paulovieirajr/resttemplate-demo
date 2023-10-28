package br.com.pcvj.dto;

import java.util.List;

public record ProductResponse(
        int id,
        String title,
        String description,
        Double price,
        Double discountPercentage,
        Double rating,
        int stock,
        String brand,
        String category,
        String thumbnail,
        List<String> images
) {
}
