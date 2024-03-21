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
		
		@Override
		public List<Ledger> getAllLedger() {
	        return ledgerdao.findAll();
	    }
		
		double prevBal;
		String transacttype;
		boolean flag;
			
		public boolean createLedger(Ledger ledger) {
            Optional<Ledger> optionalEntity = ledgerdao.findFirstByOrderByEntryidDesc();
	        
	        if (optionalEntity.isPresent()) {
	            Ledger ledgerprev = optionalEntity.get();
	            prevBal=ledgerprev.getBalance();
	        }
	        else {
	        	 prevBal=0;
	        }
	    
	        if(prevBal==0){
	    		transacttype=ledger.getTransactiontype();
	    		flag=true;
	    		
	    		if(transacttype!= null && transacttype.equals("CREDIT")) {
	    			ledger.setBalance(ledger.getAmount());
	    			ledgerdao.save(ledger);
	    		}
	    		else if(transacttype!= null && transacttype.equals("DEBIT")) {
	    			ledger.setBalance(ledger.getBalance()-ledger.getAmount());
	    			ledgerdao.save(ledger);
	    		}
	    		else {
	    			flag=false;
	    		}
    	}
    	
    	else if(prevBal!=0) {
    		transacttype=ledger.getTransactiontype();
    		flag=true;
    		
    		if(transacttype!= null && transacttype.equals("CREDIT")) {
    			ledger.setBalance(prevBal + ledger.getAmount());
    			ledgerdao.save(ledger);
    		}
    		else if(transacttype!= null && transacttype.equals("DEBIT")) {
    			ledger.setBalance(prevBal - ledger.getAmount());
    			ledgerdao.save(ledger);
    		}
    		else {
    			flag=false;
    		}
    	}
			return flag;	
		}

}


