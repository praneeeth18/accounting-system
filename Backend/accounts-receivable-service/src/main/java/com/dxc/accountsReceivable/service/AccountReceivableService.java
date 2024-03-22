package com.dxc.accountsReceivable.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dxc.accountsReceivable.model.AccountReceivable;

public interface AccountReceivableService {
	
	public ResponseEntity<?> createReceivable(AccountReceivable accountReceivable);
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable();
	public ResponseEntity<List<AccountReceivable>> findByCompanyId(int companyId);
	public ResponseEntity<AccountReceivable> getInvoiceById(long id);
}
