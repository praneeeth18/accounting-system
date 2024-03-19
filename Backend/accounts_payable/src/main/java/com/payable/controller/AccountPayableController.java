package com.payable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payable.model.AccountPayable;
import com.payable.service.AccountPayableService;

@RestController
@RequestMapping
public class AccountPayableController {
	
	@Autowired
	private AccountPayableService accountPayableService;
	
	@GetMapping("/payable")
	public List<AccountPayable> getAllAccountPayable() {
		return accountPayableService.getAllAccountPayable();
	}
	
	@GetMapping("/payable/{payableId}")
    public ResponseEntity<AccountPayable> getPurchaseById(@PathVariable("payableId") Long payableId) {
		AccountPayable accountPayable = accountPayableService.getAccountPayableById(payableId);
        return ResponseEntity.ok(accountPayable);
    }
	
	@PostMapping("/account-payable")
    public ResponseEntity<AccountPayable> createPurchase(@RequestBody AccountPayable accountPayable) {
		AccountPayable createdAccountPayable = accountPayableService.createAccountPayable(accountPayable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountPayable);
    }
	
	@DeleteMapping("/payable/{payableId}")
    public ResponseEntity<Void> deletePurchase(@PathVariable("payableId") Long payableId) {
		accountPayableService.deleteAccountPayable(payableId);
        return ResponseEntity.noContent().build();
    }

}
