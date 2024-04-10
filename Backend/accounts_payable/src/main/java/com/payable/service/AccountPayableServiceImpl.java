package com.payable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payable.dao.AccountPayableDao;
import com.payable.feign.UserServiceFeingInterface;
import com.payable.model.AccountPayable;

@Service
public class AccountPayableServiceImpl implements AccountPayableService {
	
	private final AccountPayableDao accountPayableDao;
    private final UserServiceFeingInterface userServiceInterface;

    public AccountPayableServiceImpl(AccountPayableDao accountPayableDao,
                                         UserServiceFeingInterface userServiceInterface) {
        this.accountPayableDao = accountPayableDao;
        this.userServiceInterface = userServiceInterface;
    }
	
    @Override
	public ResponseEntity<String> createPayable(AccountPayable accountPayable) {
		try {
			// Check if the company ID exists
	        ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(accountPayable.getCompanyId());
	        if (companyResponse.getStatusCode() != HttpStatus.OK) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company ID!");
	        }
			accountPayable.setTotalAmount(accountPayable.getQuantity() * accountPayable.getPrice());
			accountPayableDao.save(accountPayable);
			return ResponseEntity.status(HttpStatus.CREATED).body("Entry created!");
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

	@Override
	public ResponseEntity<AccountPayable> getInvoiceById(long id) {
		try {
			// Find the invoice by ID
	        AccountPayable invoice = accountPayableDao.findById(id).orElse(null);
	        
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
	public ResponseEntity<String> updatePayable(Long payableId, AccountPayable updatedPayable) {
	    try {
	        // Check if the receivableId exists
	        if (!accountPayableDao.existsById(payableId)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payable with ID " + payableId + " not found.");
	        }

	        // Check if the company ID exists
	        ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(updatedPayable.getCompanyId());
	        if (companyResponse.getStatusCode() != HttpStatus.OK) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company ID!");
	        }

	        // Update the receivable
	        AccountPayable existingPayable = accountPayableDao.findById(payableId).orElse(null);
	        if (existingPayable != null) {
	            existingPayable.setInvoiceNumber(updatedPayable.getInvoiceNumber());
	            existingPayable.setProductDescription(updatedPayable.getProductDescription());
	            existingPayable.setQuantity(updatedPayable.getQuantity());
	            existingPayable.setPrice(updatedPayable.getPrice());
	            existingPayable.setDueDate(updatedPayable.getDueDate());
	            existingPayable.setTotalAmount(updatedPayable.getQuantity() * updatedPayable.getPrice());
	            existingPayable.setStatus(updatedPayable.getStatus());
	            existingPayable.setVendorName(updatedPayable.getVendorName());
	            existingPayable.setCompanyId(updatedPayable.getCompanyId());

	            accountPayableDao.save(existingPayable);
	            return ResponseEntity.status(HttpStatus.OK).body("Payable updated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payable with ID " + payableId + " not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating the Payable.");
	    }
	}
	
}
