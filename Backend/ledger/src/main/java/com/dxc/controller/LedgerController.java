package com.dxc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.feign.UserServiceFeignInterface;
import com.dxc.model.Ledger;


import com.dxc.service.LedgerServices;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
	
	private final LedgerServices ledgerservice;
	private final UserServiceFeignInterface userServiceInterface;
	
	public LedgerController(LedgerServices ledgerservice, UserServiceFeignInterface userServiceInterface) {
        this.ledgerservice = ledgerservice;
        this.userServiceInterface = userServiceInterface;
    }
	
	@GetMapping("/getAllEntries")
    public ResponseEntity<List<Ledger>> getAllLedger() {
		try {
            List<Ledger> ledger = ledgerservice.getAllLedger();
            if (ledger.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
            return new ResponseEntity<>(ledger, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/getLedgerByCompanyId/{companyId}")
	public ResponseEntity<List<Ledger>> getLedgerByCompanyId(@PathVariable Integer companyId){
		try {
            List<Ledger> ledger1 = ledgerservice.getLedgerByCompanyId(companyId);
            if (ledger1.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
            return new ResponseEntity<>(ledger1, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	@PostMapping("/addEntry")
    public ResponseEntity<Ledger> postLedger(@RequestBody Ledger ledger) {
		try {
			ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(ledger.getCompanyId());
	        if (companyResponse.getStatusCode() != HttpStatus.OK) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
            boolean flag = ledgerservice.createLedger(ledger);
            if (flag) {
                return new ResponseEntity<>(ledger, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
}
