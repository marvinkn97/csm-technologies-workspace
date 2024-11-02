package model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BillDetailsEntity {
    private Integer id;
    private String customerName;
    private String customerPhone;
    private BigDecimal totalAmount;
    private Date billingDate;
}
