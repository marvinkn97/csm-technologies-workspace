package model;

import lombok.Data;

@Data
public class BillProductEntity {
    private Integer id;
    private BillDetailsEntity billDetailsEntity;
    private ProductEntity productEntity;
    private Integer noOfUnits;
}
