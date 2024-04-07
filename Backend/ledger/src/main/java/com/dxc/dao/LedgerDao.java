package com.dxc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Ledger;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Long>{
	
	Optional<Ledger> findFirstByCompanyIdOrderByEntryidDesc(Integer companyId);
	List<Ledger> findByCompanyId(Integer companyId);
	
}
