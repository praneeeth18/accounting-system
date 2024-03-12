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
import com.dxc.service.LedgerService;

@RestController
@RequestMapping
public class LedgerController {
	
	@Autowired
	private  LedgerService ledgerservice;
	
	@GetMapping("/ledger")
    public List<Ledger> getAllLedger() {
        return ledgerservice.getAllLedger();
    }
	
	@GetMapping("/ledger/{companyid}")
	public List<Ledger> getLedger(@PathVariable String companyid) {
	    return ledgerservice.getLedger(companyid);
	}
	
	 @PostMapping("/ledger")
	    public Ledger entryLedger(@RequestBody Ledger ledger) {
	    	System.out.println(ledger.toString());
	        return ledgerservice.createLedgerEntry(ledger);
	    }

}
