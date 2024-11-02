package dao;

import model.ProductEntity;

import java.util.Collection;

public interface ProductDao {
    Collection<ProductEntity> getAll();
}
