package com.dxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.dao.LedgerDao;
import com.dxc.model.Ledger;

@Service
public class LedgerService {

	@Autowired
    private LedgerDao ledgerdao;
	
	public List<Ledger> getAllLedger() {
        return ledgerdao.findAll();
    }
	
	public Ledger createLedgerEntry(Ledger ledger) {
        return ledgerdao.save(ledger);
    }
	
	public List<Ledger> getLedger(String companyid) {
        return ledgerdao.findBycompanyid(companyid);
    }
	
}
