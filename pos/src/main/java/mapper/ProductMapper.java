package mapper;

import dto.ProductDto;
import model.ProductEntity;

public class ProductMapper {
    public static ProductDto mapToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId().toString());
        productDto.setName(productEntity.getName());
        productDto.setQuantity(productEntity.getQuantity().toString());
        productDto.setUnitPrice(productEntity.getUnitPrice().toString());
        return productDto;
    }
}
