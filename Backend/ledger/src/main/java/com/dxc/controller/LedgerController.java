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
	 boolean flag;
	
	@Autowired
	private  LedgerServices ledgerservice;
	
	@GetMapping("/ledger")
    public ResponseEntity<?> getAllLedger() {
		List<Ledger> ledger= ledgerservice.getAllLedger();
		response=new ResponseEntity<List>(ledger, HttpStatus.ACCEPTED);
        return response;
    }
	
	@GetMapping("/ledger/{companyId}")
	public ResponseEntity<?> getLedgerByCompanyId(@PathVariable Integer companyId){
		List<Ledger> ledger1= ledgerservice.getLedgerByCompanyId(companyId);
			response=new ResponseEntity<List>(ledger1, HttpStatus.ACCEPTED);
		return response;
		
	}
	
	@PostMapping("/ledger")
    public ResponseEntity<?> postLedger(@RequestBody Ledger ledger) {
		flag=ledgerservice.createLedger(ledger);
		
		if(flag) {
			response=new ResponseEntity<Ledger>(ledger, HttpStatus.ACCEPTED);
		}
		else {
			response = new ResponseEntity<String>("Check the details ", HttpStatus.BAD_REQUEST);
		}
		return response;
    }
	
	@Autowired
	private RestTemplate restTemplate;	
	
	@GetMapping("/{companyId}")
	public ResponseEntity<?> getDetailsByCompanyId(@PathVariable Integer companyId){
		Optional<User> user = null;
		String url = "http://localhost:8080/user/getDetailsByCompanyId/"+companyId;
		
		try {
			user =restTemplate.getForObject(url, Optional.class);
			response=new ResponseEntity<Optional>(user, HttpStatus.ACCEPTED);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
	 
}
