package com.dxc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dxc.dao.LedgerDao;
import com.dxc.model.Ledger;

@Service
public class LedgerServiceImpl implements LedgerServices{
	
		private final LedgerDao ledgerdao;
		
		public LedgerServiceImpl(LedgerDao ledgerdao) {
	        this.ledgerdao = ledgerdao;
	    }
		
		@Override
		public List<Ledger> getAllLedger() {
	        return ledgerdao.findAll();
	    }
		
		@Override
	    public boolean createLedger(Ledger ledger) {
	        Optional<Ledger> optionalEntity = ledgerdao.findFirstByCompanyIdOrderByEntryidDesc(ledger.getCompanyId());
	        
	        double previousBal = 0.0;
	        if (optionalEntity.isPresent()) {
	            Ledger previousledger = optionalEntity.get();
	            previousBal = previousledger.getBalance();
	        }

	        String transactiontype = ledger.getTransactiontype();
	        boolean flag = true;
	        
	        if (transactiontype != null) {                   
	            if (transactiontype.equals("CREDIT")) {
	                ledger.setBalance(previousBal + ledger.getAmount());
	            } 
	            else if (transactiontype.equals("DEBIT")) {
	                ledger.setBalance(previousBal - ledger.getAmount());
	            } 
	            else {
	                flag = false;
	            }
	        } 
	        else {
	            flag = false;
	        }

	        if (flag) {
	            ledgerdao.save(ledger);
	        }
	        
	        return flag;
	    }

		@Override
		public List<Ledger> getLedgerByCompanyId(Integer companyId) {
			return ledgerdao.findByCompanyId(companyId);
		}

}


