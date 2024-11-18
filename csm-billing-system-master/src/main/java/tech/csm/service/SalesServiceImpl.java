package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.model.SalesMaster;
import tech.csm.repository.SalesMasterRepo;


@Service
public class SalesServiceImpl implements SalesService {
	
	
	@Autowired
	private SalesMasterRepo salesMasterRepo;

	@Override
	public void saveSale(SalesMaster salesMaster) {
		// TODO Auto-generated method stub
		salesMasterRepo.save(salesMaster);
		
	}

}
