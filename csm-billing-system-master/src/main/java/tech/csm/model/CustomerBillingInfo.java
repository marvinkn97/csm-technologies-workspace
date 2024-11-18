package tech.csm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBillingInfo {
	
	private String customerName;
	private Long numOfItems;
	private Double totalAmount;

}
