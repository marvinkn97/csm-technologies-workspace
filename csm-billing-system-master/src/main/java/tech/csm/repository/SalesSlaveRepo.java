package tech.csm.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.model.CustomerBillingInfo;
import tech.csm.model.SalesSlave;

@Repository
public interface SalesSlaveRepo extends JpaRepository<SalesSlave, Integer> {
	
	  @Query("SELECT new tech.csm.model.CustomerBillingInfo(s.customerName, COUNT(ss), SUM(ss.itemMaster.pricePerUnit * ss.salesQty)) " +
	           "FROM SalesMaster s " +
	           "JOIN s.salesSlaves ss " +
	           "WHERE s.dateOfSales = :date " +
	           "GROUP BY s.customerName")
	    List<CustomerBillingInfo> getTotalBillingInfoForDate(@Param("date") Date date);

}
