package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.model.SalesMaster;

@Repository
public interface SalesMasterRepo extends JpaRepository<SalesMaster, Integer> {

}
