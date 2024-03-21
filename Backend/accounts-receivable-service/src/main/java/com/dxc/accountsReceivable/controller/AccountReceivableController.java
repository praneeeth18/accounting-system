package com.dxc.accountsReceivable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.accountsReceivable.model.AccountReceivable;
import com.dxc.accountsReceivable.service.AccountReceivableServiceImpl;

@RestController
@RequestMapping("/accountReceivable")
@CrossOrigin(origins="http://localhost:4200/")
public class AccountReceivableController {

	@Autowired
	private AccountReceivableServiceImpl accountReceivableServiceImpl;
	
	@PostMapping("/createAccountReceivable")
	public ResponseEntity<?> createReceivable(@RequestBody AccountReceivable accountReceivable) {
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
}
