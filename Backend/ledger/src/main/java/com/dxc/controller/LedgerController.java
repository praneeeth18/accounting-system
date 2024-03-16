package com.dxc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class LedgerController {
	
	@Autowired
	private  LedgerServices ledgerservice;
	
	@GetMapping("/ledger")
    public List<Ledger> getAllLedger() {
        return ledgerservice.getAllLedger();
    }
	
	@GetMapping("/prevbalance")
	public double getPrevBalance() {
		return ledgerservice.getPreviousBalance();
	}
	
	String trtype;
	double prevBal;
	
	 @PostMapping("/ledger")
	    public Ledger entryLedger(@RequestBody Ledger ledger) {
		 
	    	System.out.println(ledger.toString());
	    	
	    	prevBal=ledgerservice.getPreviousBalance();
	    	
	    	if(prevBal==0){
	   
		    		trtype=ledger.getTransactiontype();
		    		
		    		if(trtype!= null && trtype.equals("CREDIT")) {
		    			System.out.println(trtype);
		    			ledger.setBalance(ledger.getAmount());
		    			System.out.println(ledger.getBalance());
		    		}
		    		
		    		else if(trtype!= null && trtype.equals("DEBIT")) {
		    			System.out.println(trtype);
		    			ledger.setBalance(ledger.getBalance()-ledger.getAmount());
		    			System.out.println(ledger.getBalance());
		    		}	
	    	}
	    	
	    	else if(prevBal!=0) {
	    		
	    		System.out.println(prevBal);
	    		trtype=ledger.getTransactiontype();
	    		
	    		if(trtype!= null && trtype.equals("CREDIT")) {
	    			System.out.println(trtype);
	    			ledger.setBalance(prevBal + ledger.getAmount());
	    			System.out.println(ledger.getBalance());
	    		}
	    		
	    		else if(trtype!= null && trtype.equals("DEBIT")) {
	    			System.out.println(trtype);
	    			ledger.setBalance(prevBal - ledger.getAmount());
	    			System.out.println(ledger.getBalance());
	    		}
	    	}
		 
	        return ledgerservice.createLedgerEntry(ledger);
	    }
	 
	 

}
