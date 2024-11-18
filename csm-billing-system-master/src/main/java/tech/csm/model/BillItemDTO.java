package tech.csm.model;

import lombok.Data;

@Data
public class BillItemDTO {
	    private String itemId;
	    private String customerName;
	    private String quantity;
	    private String pricePerUnit;
	    private String totalAmount;
	    private String dateOfSales;
}
