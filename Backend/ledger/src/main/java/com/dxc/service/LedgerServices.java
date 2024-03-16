package com.dxc.service;

import java.util.List;

import com.dxc.model.Ledger;

public interface LedgerServices {
	
	public List<Ledger> getAllLedger();
	
	public Ledger createLedgerEntry(Ledger ledger);

	public double getPreviousBalance();
	
}
