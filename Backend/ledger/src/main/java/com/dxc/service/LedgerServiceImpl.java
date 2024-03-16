package com.dxc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.dao.LedgerDao;
import com.dxc.model.Ledger;


@Service
public class LedgerServiceImpl implements LedgerServices{
	
		@Autowired
	    private LedgerDao ledgerdao;
		
		String type;
		boolean flag;
		double ExBal;
		double Amt;
		
		@Override
		public List<Ledger> getAllLedger() {
	        return ledgerdao.findAll();
	    }
		
		@Override
		public Ledger createLedgerEntry(Ledger ledger) {
	    		return ledgerdao.save(ledger);
	    		
	    }
		
		double prevBal;
		
		@Override
		public double getPreviousBalance() {
			// Find the last entry
	        Optional<Ledger> optionalEntity = ledgerdao.findFirstByOrderByEntryidDesc();
	        
	        if (optionalEntity.isPresent()) {
	            Ledger ledger = optionalEntity.get();
	            prevBal=ledger.getBalance();
	        }
	        
	        else {
	        	//throw new RuntimeException("No entries found in the database.");
	        	 prevBal=0;
	        }
		return prevBal;
	}

}


