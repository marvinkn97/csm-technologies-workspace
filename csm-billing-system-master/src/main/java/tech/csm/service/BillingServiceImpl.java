package tech.csm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tech.csm.model.BillItemDTO;
import tech.csm.model.CustomerBillingInfo;
import tech.csm.model.ItemMaster;
import tech.csm.model.SalesMaster;
import tech.csm.model.SalesSlave;
import tech.csm.repository.ItemMasterRepo;
import tech.csm.repository.SalesMasterRepo;
import tech.csm.repository.SalesSlaveRepo;


@Service
public class BillingServiceImpl implements BillingService {
	
	@Autowired
	private SalesMasterRepo salesMasterRepo;
	
	@Autowired
	private ItemMasterRepo itemMasterRepo;
	
	@Autowired
	private SalesSlaveRepo salesSlaveRepo;

	@Override
	@Transactional
	public void saveBill(List<BillItemDTO> items) {
		// TODO Auto-generated method stub
		

	            // Iterate through bill items and save each one
	            for (BillItemDTO item : items) {
	            	
	            var saleMaster = new SalesMaster();
	            saleMaster.setCustomerName(item.getCustomerName());
	           
	            
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                Date dateOfSales = dateFormat.parse(item.getDateOfSales());
	                saleMaster.setDateOfSales(dateOfSales);
	            } catch (ParseException e) {
	                System.out.println("Error parsing date: " + e.getMessage());
	            }
	            
	            var savedSale = salesMasterRepo.save(saleMaster);
	            
	            
	            ItemMaster itemMaster = itemMasterRepo.findById(Integer.parseInt(item.getItemId())).orElse(null);
	            
	            int newQty = itemMaster.getQuantity() - Integer.parseInt(item.getQuantity());
	            
	            itemMaster.setQuantity(newQty);
	            
	            var savedItem = itemMasterRepo.save(itemMaster);
	            
	            var salesSlave = new SalesSlave();
	            salesSlave.setItemMaster(savedItem);
	            salesSlave.setSalesMaster(savedSale);
	            salesSlave.setSalesQty(Integer.parseInt(item.getQuantity()));
	            	
	             salesSlaveRepo.save(salesSlave);
	           
	            }
		 }

	@Override
	public List<CustomerBillingInfo> getCustomerBillingInfoByDate(Date date) {
		// TODO Auto-generated method stub
		 return salesSlaveRepo.getTotalBillingInfoForDate(date);
	}

}
