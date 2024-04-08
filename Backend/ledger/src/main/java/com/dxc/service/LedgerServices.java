package com.dxc.service;

import java.util.List;

import com.dxc.model.Ledger;

public interface LedgerServices {
	
	 List<Ledger> getAllLedger();
	 boolean createLedger(Ledger ledger);
	 List<Ledger> getLedgerByCompanyId(Integer companyId);
	
}
