package com.dxc.accountsReceivable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.accountsReceivable.feign.AccountReceivableFeignInterface;
import com.dxc.accountsReceivable.model.AccountReceivable;
import com.dxc.accountsReceivable.repository.AccountReceivableRepository;

@Service
public class AccountReceivableServiceImpl implements AccountReceivableService{
	
	@Autowired
	private AccountReceivableRepository accountReceivableRepository;
	
	@Autowired
	private AccountReceivableFeignInterface userServiceInterface;

	@Override
	public ResponseEntity<?> createReceivable(AccountReceivable accountReceivable) {
		try {
			// Check if the company ID exists
	        ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(accountReceivable.getCompanyId());
	        if (companyResponse.getStatusCode() != HttpStatus.OK) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company ID!");
	        }
			accountReceivable.setAmount(accountReceivable.getQuantity() * accountReceivable.getPrice());
			accountReceivableRepository.save(accountReceivable);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Entry created!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating the entry!");
		}
	}

	@Override
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable() {
		try {
//			return ResponseEntity.status(HttpStatus.OK).body(accountReceivableRepository.findAll());
			List<AccountReceivable> entries = accountReceivableRepository.findAll();
			if (entries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()); // Return 404 if no data found
            }
			return ResponseEntity.status(HttpStatus.OK).body(entries);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<List<AccountReceivable>> findByCompanyId(int companyId) {
		try {
			List<AccountReceivable> entries = accountReceivableRepository.findByCompanyId(companyId);
			if (entries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()); // Return 404 if no data found
            }
            return ResponseEntity.status(HttpStatus.OK).body(entries);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<AccountReceivable> getInvoiceById(long id) {
		try {
			// Find the invoice by ID
	        AccountReceivable invoice = accountReceivableRepository.findById(id).orElse(null);
	        
	        // Check if the invoice exists
	        if (invoice != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(invoice); // Return the invoice if found
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	
	
	

}
