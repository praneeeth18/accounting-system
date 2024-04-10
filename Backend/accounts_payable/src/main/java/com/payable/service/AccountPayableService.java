package com.payable.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.payable.model.AccountPayable;

public interface AccountPayableService {	
	public ResponseEntity<String> createPayable(AccountPayable accountPayable);
	public ResponseEntity<List<AccountPayable>> getAllAccountPayable();
	public ResponseEntity<List<AccountPayable>> findByCompanyId(int companyId);
	public ResponseEntity<AccountPayable> getInvoiceById(long id);
	public ResponseEntity<String> updatePayable(Long payableId, AccountPayable updatedPayable);

}
