package tech.csm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tech.csm.model.BillItemDTO;
import tech.csm.model.CustomerBillingInfo;
import tech.csm.model.ItemMaster;
import tech.csm.service.BillingService;
import tech.csm.service.ItemService;

@Controller
public class BillingController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private BillingService billingService;
	
	
	@GetMapping()
	public String homePage() {
		return "redirect:/billing";
	}

	@GetMapping("/billing")
	public String showBillingPage(Model model) {
		List<ItemMaster> items = itemService.getAllItems();
		model.addAttribute("items", items);
		return "billingPage";
	}

	@GetMapping("/getItemPrice")
	public ResponseEntity<ItemMaster> getItemPrice(@RequestParam Integer itemId) {
		ItemMaster item = itemService.getItemById(itemId);
		return ResponseEntity.status(HttpStatus.OK).body(item);
	}
	
	@PostMapping("/saveBill")
    public ResponseEntity<String> saveBill(@RequestBody List<BillItemDTO> billItems) {
//		System.out.println(billItems);
       
		billingService.saveBill(billItems);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Bill saved successfully");
    }
	
	@GetMapping("/summary")
	public String getSummaryPage() {
		return "summaryPage";
		
	}
	
	
	 @GetMapping("/customerBilling")
	    public ResponseEntity<Object> showCustomerBillingPage(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
	                                         Model model) {
	     System.out.println(date);	  
		 
		 if (date == null) {
	            date = new Date(); // Default to current date if not provided
	        }

	        List<CustomerBillingInfo> customerBillingInfos = billingService.getCustomerBillingInfoByDate(date);
	        model.addAttribute("customerBillingInfos", customerBillingInfos);
	        model.addAttribute("selectedDate", date); // Send selected date back to the view

	        System.out.println(customerBillingInfos);
	        
	        return ResponseEntity.status(HttpStatus.OK).body(customerBillingInfos);
	    }

	
}
