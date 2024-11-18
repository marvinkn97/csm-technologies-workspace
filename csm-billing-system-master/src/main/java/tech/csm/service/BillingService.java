package tech.csm.service;

import java.util.Date;
import java.util.List;

import tech.csm.model.BillItemDTO;
import tech.csm.model.CustomerBillingInfo;


public interface BillingService {
	
	void saveBill(List<BillItemDTO> items);

	List<CustomerBillingInfo> getCustomerBillingInfoByDate(Date date);

}
