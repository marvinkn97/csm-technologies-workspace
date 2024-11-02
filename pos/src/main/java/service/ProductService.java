package service;

import dto.BillProductDto;
import dto.ProductDto;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    Collection<ProductDto> getAll();

}
