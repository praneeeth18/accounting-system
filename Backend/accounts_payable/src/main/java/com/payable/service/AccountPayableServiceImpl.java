package com.payable.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payable.dao.AccountPayableDao;
import com.payable.model.AccountPayable;

@Service
public class AccountPayableServiceImpl implements AccountPayableService {
	@Autowired
	private AccountPayableDao accountPayableDao;
	
	@Override
	public ResponseEntity<?> createPayable(AccountPayable accountPayable) {
		try {
			
	        
			accountPayable.setTotalAmount(accountPayable.getQuantity() * accountPayable.getPrice());
			accountPayableDao.save(accountPayable);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Entry created!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating the entry!");
		}
	}
	
	@Override
	public ResponseEntity<List<AccountPayable>> getAllAccountPayable() {
		try {
			List<AccountPayable> entries = accountPayableDao.findAll();
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
	public ResponseEntity<AccountPayable> getInvoiceById(long payableId) {
		try {
			// Find the invoice by ID
	        AccountPayable invoice = accountPayableDao.findById(payableId).orElse(null);
	        
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

	@Override
	public ResponseEntity<List<AccountPayable>> findByCompanyId(int companyId) {
		try {
			List<AccountPayable> entries = accountPayableDao.findByCompanyId(companyId);
			if (entries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()); // Return 404 if no data found
            }
            return ResponseEntity.status(HttpStatus.OK).body(entries);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
}
