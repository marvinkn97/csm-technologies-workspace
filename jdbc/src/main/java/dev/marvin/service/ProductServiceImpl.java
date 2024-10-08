package dev.marvin.service;

import dev.marvin.dao.ProductDao;
import dev.marvin.dao.ProductDaoJdbcImpl;
import dev.marvin.dto.ProductDto;
import dev.marvin.mapper.ProductMapper;
import dev.marvin.model.ProductEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoJdbcImpl();

    @Override
    public String addProduct(ProductDto productDto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date mfgDate = simpleDateFormat.parse(productDto.manufacturingDate());
            ProductEntity product = new ProductEntity(null, productDto.productName(), new BigDecimal(productDto.unitPrice()), mfgDate);
            int generated = productDao.create(product);
            return "Product saved successfully with id %s".formatted(generated);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ProductDto> getAllProducts() {
        return productDao.getAllProducts().stream().map(ProductMapper::mapTODto).toList();
    }

    @Override
    public ProductDto getProductById(String productId) {
        Optional<ProductEntity> optionalProduct = productDao.getAllProducts().stream()
                .filter(productEntity -> productEntity.productId().equals(Integer.valueOf(productId))).findFirst();

        if (optionalProduct.isPresent()) {
            ProductEntity productEntity = optionalProduct.get();
            return ProductMapper.mapTODto(productEntity);
        } else {
            return null;
        }

    }

    @Override
    public String deleteProductById(String productId) {
        int rowsAffected = productDao.delete(productId);
        if (rowsAffected > 0) {
            return "Product deleted successfully";
        } else {
            return "Failed to delete product";
        }
    }

    @Override
    public String updateProductById(ProductDto productDto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date mfgDate = simpleDateFormat.parse(productDto.manufacturingDate());
            ProductEntity product = new ProductEntity(Integer.parseInt(productDto.productId()), productDto.productName(), new BigDecimal(productDto.unitPrice()), mfgDate);
            int rowsAffected = productDao.update(product);
            if (rowsAffected > 0) {
                return "Product updated successfully";
            } else {
                return "Failed to update product";
            }


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
