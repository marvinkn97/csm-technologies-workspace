package tech.csm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.ItemMaster;
import tech.csm.repository.ItemMasterRepo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMasterRepo itemRepo;

	@Override
	public List<ItemMaster> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepo.findAll();
	}

	@Override
	public ItemMaster getItemById(Integer id) {
		// TODO Auto-generated method stub
		return itemRepo.findById(id).orElse(null);
	}
	
	

}
