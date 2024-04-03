package com.dxc.service;

import java.util.List;

import com.dxc.model.Ledger;

public interface LedgerServices {
	
	public List<Ledger> getAllLedger();
	
	public boolean createLedger(Ledger ledger);
	
	public List<Ledger> getLedgerByCompanyId(Integer companyId);
	
}
