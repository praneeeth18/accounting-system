package com.dxc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.model.Ledger;

@Repository
public interface LedgerDao extends JpaRepository<Ledger, Long>{
	List<Ledger> findBycompanyid(String companyid);
}
