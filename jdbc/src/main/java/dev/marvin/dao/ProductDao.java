package dev.marvin.dao;

import dev.marvin.model.ProductEntity;

import java.util.Collection;

public interface ProductDao {
    Integer create(ProductEntity product);
    Collection<ProductEntity> getAllProducts();
    Integer delete(String productId);
    Integer update(ProductEntity productEntity);
}
