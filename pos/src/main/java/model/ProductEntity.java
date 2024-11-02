package model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
}
