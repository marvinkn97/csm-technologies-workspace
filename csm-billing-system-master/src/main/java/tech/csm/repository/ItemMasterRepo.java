package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.model.ItemMaster;

@Repository
public interface ItemMasterRepo extends JpaRepository<ItemMaster, Integer> {
	
	ItemMaster findByItemName(String name);

}
