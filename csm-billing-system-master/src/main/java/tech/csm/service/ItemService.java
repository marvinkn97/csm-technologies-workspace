package tech.csm.service;

import java.util.List;

import tech.csm.model.ItemMaster;

public interface ItemService {
	List<ItemMaster> getAllItems();
	
	ItemMaster getItemById(Integer id);
	
}
