package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillDetailsDto {
    private String customerName;
    private String customerPhone;
    private String totalAmount;
}
