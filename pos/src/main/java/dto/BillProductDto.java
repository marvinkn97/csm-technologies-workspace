package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillProductDto {
    private ProductDto productDto;
    private String noOfUnits;
}
