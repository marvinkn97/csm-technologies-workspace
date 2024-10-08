package dev.marvin.dto;

public record ProductDto(
        String productId,
        String productName,
        String unitPrice,
        String manufacturingDate
) {

}
