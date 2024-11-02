package service;

import dao.ProductDao;
import dao.ProductDaoImpl;
import dto.ProductDto;
import mapper.ProductMapper;

import java.util.Collection;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public Collection<ProductDto> getAll() {
        return productDao.getAll().stream()
                .map(ProductMapper::mapToDto)
                .toList();
    }

}
