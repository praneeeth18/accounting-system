package com.dxc.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.client.RestTemplate;

import com.dxc.model.Ledger;
import com.dxc.model.User;

import com.dxc.service.LedgerServices;

@RestController
@RequestMapping
@CrossOrigin(origins="http://localhost:4200")
public class LedgerController {
	
	ResponseEntity response;
	
	@Autowired
	private RestTemplate restTemplate;	
	
	@Autowired
	private  LedgerServices ledgerservice;
	
	@GetMapping("/ledger")
    public ResponseEntity<List<Ledger>> getAllLedger() {
		List<Ledger> ledger= ledgerservice.getAllLedger();
		response=new ResponseEntity<List<Ledger>>(ledger, HttpStatus.ACCEPTED);
        return response;
    }
	
	@GetMapping("/ledger/{companyId}")
	public ResponseEntity<List<Ledger>> getLedgerByCompanyId(@PathVariable Integer companyId){
		List<Ledger> ledger1= ledgerservice.getLedgerByCompanyId(companyId);
			response=new ResponseEntity<List<Ledger>>(ledger1, HttpStatus.ACCEPTED);
		return response;
		
	}
	
	@PostMapping("/ledger")
    public ResponseEntity<Ledger> postLedger(@RequestBody Ledger ledger) {
		boolean flag;
		flag=ledgerservice.createLedger(ledger);
		
		if(flag) {
			response=new ResponseEntity<Ledger>(ledger, HttpStatus.ACCEPTED);
		}
		else {
			response = new ResponseEntity<String>("Check the details ", HttpStatus.BAD_REQUEST);
		}
		return response;
    }
	
	@GetMapping("/{companyId}")
	public ResponseEntity<Optional<User>> getDetailsByCompanyId(@PathVariable Integer companyId){
		String url = "http://localhost:8080/user/getDetailsByCompanyId/"+companyId;
		
		try {
			Optional<User> user =restTemplate.getForObject(url, Optional.class);
			response=new ResponseEntity<Optional<User>>(user, HttpStatus.ACCEPTED);
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
		}
		return response;
	}
	 
}
