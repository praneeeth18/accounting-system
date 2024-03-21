package com.dxc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.Ledger;
import com.dxc.service.LedgerServices;

@RestController
@RequestMapping
@CrossOrigin(origins="http://localhost:4200")
public class LedgerController {
	
	@Autowired
	private  LedgerServices ledgerservice;
	
	@GetMapping("/ledger")
    public List<Ledger> getAllLedger() {
        return ledgerservice.getAllLedger();
    }
	
	String transacttype;
	double prevBal;
	
	 @PostMapping("/ledger")
	 public Ledger entryLedger(@RequestBody Ledger ledger) {
	    	
	    prevBal=ledgerservice.getPreviousBalance();
	    	
	    	if(prevBal==0){
		    		transacttype=ledger.getTransactiontype();
		    		
		    		if(transacttype!= null && transacttype.equals("CREDIT")) {
		    			ledger.setBalance(ledger.getAmount());
		    		}
		    		
		    		else if(transacttype!= null && transacttype.equals("DEBIT")) {
		    			ledger.setBalance(ledger.getBalance()-ledger.getAmount());
		    		}	
	    	}
	    	
	    	else if(prevBal!=0) {
	    		transacttype=ledger.getTransactiontype();
	    		
	    		if(transacttype!= null && transacttype.equals("CREDIT")) {
	    			ledger.setBalance(prevBal + ledger.getAmount());
	    		}
	    		
	    		else if(transacttype!= null && transacttype.equals("DEBIT")) {
	    			ledger.setBalance(prevBal - ledger.getAmount());
	    		}
	    	}
		 
	        return ledgerservice.createLedgerEntry(ledger);
	    }
}
