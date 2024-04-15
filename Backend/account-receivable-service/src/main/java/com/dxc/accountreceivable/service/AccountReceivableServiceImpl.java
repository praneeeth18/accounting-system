package com.dxc.accountreceivable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.accountreceivable.feign.UserServiceFeignInterface;
import com.dxc.accountreceivable.model.AccountReceivable;
import com.dxc.accountreceivable.repository.AccountReceivableRepository;

@Service
public class AccountReceivableServiceImpl implements AccountReceivableService{
	
	private final AccountReceivableRepository accountReceivableRepository;
    private final UserServiceFeignInterface userServiceInterface;

    public AccountReceivableServiceImpl(AccountReceivableRepository accountReceivableRepository,
    		UserServiceFeignInterface userServiceInterface) {
        this.accountReceivableRepository = accountReceivableRepository;
        this.userServiceInterface = userServiceInterface;
    }
    
    @Override
    public ResponseEntity<String> createReceivable(AccountReceivable accountReceivable) {
        try {
            // Check if the company ID exists
            ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(accountReceivable.getCompanyId());
            if (companyResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company ID!");
            }
     
            accountReceivable.setAmount(accountReceivable.getQuantity() * accountReceivable.getPrice());
     
            AccountReceivable savedReceivable = accountReceivableRepository.save(accountReceivable);

            String invoiceNumber = "ACCR" + savedReceivable.getReceivableId();
     
            savedReceivable.setInvoiceNumber(invoiceNumber);
            accountReceivableRepository.save(savedReceivable);
     
            return ResponseEntity.status(HttpStatus.CREATED).body("Entry created!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating the entry!");
        }
    }

	@Override
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable() {
		try {
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

	@Override
	public ResponseEntity<String> updateReceivable(Long receivableId, AccountReceivable updatedReceivable) {
	    try {
	        // Check if the receivableId exists
	        if (!accountReceivableRepository.existsById(receivableId)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receivable with ID " + receivableId + " not found.");
	        }

	        // Check if the company ID exists
	        ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(updatedReceivable.getCompanyId());
	        if (companyResponse.getStatusCode() != HttpStatus.OK) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company ID!");
	        }

	        // Update the receivable
	        AccountReceivable existingReceivable = accountReceivableRepository.findById(receivableId).orElse(null);
	        if (existingReceivable != null) {
	            existingReceivable.setInvoiceNumber(updatedReceivable.getInvoiceNumber());
	            existingReceivable.setProductDescription(updatedReceivable.getProductDescription());
	            existingReceivable.setQuantity(updatedReceivable.getQuantity());
	            existingReceivable.setPrice(updatedReceivable.getPrice());
	            existingReceivable.setDueDate(updatedReceivable.getDueDate());
	            existingReceivable.setAmount(updatedReceivable.getQuantity() * updatedReceivable.getPrice());
	            existingReceivable.setStatus(updatedReceivable.getStatus());
	            existingReceivable.setCustomerName(updatedReceivable.getCustomerName());
	            existingReceivable.setCompanyId(updatedReceivable.getCompanyId());

	            accountReceivableRepository.save(existingReceivable);
	            return ResponseEntity.status(HttpStatus.OK).body("Receivable updated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receivable with ID " + receivableId + " not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating the receivable.");
	    }
	}


	
	
	

}
