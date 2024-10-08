package dev.marvin.mapper;

import dev.marvin.dto.ProductDto;
import dev.marvin.model.ProductEntity;

import java.text.SimpleDateFormat;

public class ProductMapper {
    public static ProductDto mapTODto(ProductEntity productEntity) {
        return new ProductDto(productEntity.productId().toString(), productEntity.productName(), productEntity.unitPrice().toString(), new SimpleDateFormat("dd/MM/yyyy").format(productEntity.manufacturingDate()));
    }
}
