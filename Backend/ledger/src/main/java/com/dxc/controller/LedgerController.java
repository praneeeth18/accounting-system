package com.dxc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	ResponseEntity response;
	boolean flag;
	
	@Autowired
	private  LedgerServices ledgerservice;
	
	@GetMapping("/ledger")
    public List<Ledger> getAllLedger() {
        return ledgerservice.getAllLedger();
    }
	
	@PostMapping("/ledger")
    public ResponseEntity<?> getLedger(@RequestBody Ledger ledger) {
		flag=ledgerservice.createLedger(ledger);
		if(flag) {
			response=new ResponseEntity<String>("Entry added to the database", HttpStatus.ACCEPTED);
		}
		else {
			response = new ResponseEntity<String>("Check the details ", HttpStatus.BAD_REQUEST);
		}
		return response;
    }
	 
}
