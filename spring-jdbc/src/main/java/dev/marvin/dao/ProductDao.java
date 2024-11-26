package dev.marvin.dao;

import dev.marvin.domain.Product;

import java.util.Collection;

public interface ProductDao {
    Collection<Product> getAll();
}
