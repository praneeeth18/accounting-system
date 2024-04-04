package com.dxc.accountreceivable.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dxc.accountreceivable.model.AccountReceivable;

public interface AccountReceivableService {
	
	public ResponseEntity<String> createReceivable(AccountReceivable accountReceivable);
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable();
	public ResponseEntity<List<AccountReceivable>> findByCompanyId(int companyId);
	public ResponseEntity<AccountReceivable> getInvoiceById(long id);
	public ResponseEntity<String> updateReceivable(Long receivableId, AccountReceivable updatedReceivable);

}
