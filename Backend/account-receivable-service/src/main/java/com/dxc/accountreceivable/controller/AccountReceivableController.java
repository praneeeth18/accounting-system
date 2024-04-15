package com.dxc.accountreceivable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.accountreceivable.model.AccountReceivable;
import com.dxc.accountreceivable.service.AccountReceivableServiceImpl;

@RestController
@RequestMapping("/accountReceivable")
public class AccountReceivableController {

	private final AccountReceivableServiceImpl accountReceivableServiceImpl;

    public AccountReceivableController(AccountReceivableServiceImpl accountReceivableServiceImpl) {
        this.accountReceivableServiceImpl = accountReceivableServiceImpl;
    }
	
	@PostMapping("/createAccountReceivable")
	public ResponseEntity<String> createReceivable(@RequestBody AccountReceivable accountReceivable) {
		return accountReceivableServiceImpl.createReceivable(accountReceivable);
	}
	
	@GetMapping("/getAllAccountReceivable")
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable() {
		return accountReceivableServiceImpl.getAllAccountReceivable();
	}
	
	@GetMapping("/getEntryByCompanyId/{companyId}")
	public ResponseEntity<List<AccountReceivable>> getEntryByCompanyId(@PathVariable int companyId) {
		return accountReceivableServiceImpl.findByCompanyId(companyId);
	}
	
	@GetMapping("/getEntryByReceivableId/{id}")
	public ResponseEntity<AccountReceivable> getInvoiceById(@PathVariable long id) {
		return accountReceivableServiceImpl.getInvoiceById(id);
	}
	
	@PutMapping("/updateReceivable/{receivableId}")
    public ResponseEntity<String> updateReceivable(@PathVariable Long receivableId, @RequestBody AccountReceivable updatedReceivable) {
        return accountReceivableServiceImpl.updateReceivable(receivableId, updatedReceivable);
    }
}

