package dev.marvin.service;

import dev.marvin.dto.ProductDto;

import java.util.Collection;

public interface ProductService {
    String addProduct(ProductDto productDto);
    Collection<ProductDto> getAllProducts();
    ProductDto getProductById(String productId);

    String deleteProductById(String productId);
    String updateProductById(ProductDto productDto);
}
