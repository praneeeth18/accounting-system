package com.payable.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.payable.model.AccountPayable;
import com.payable.service.AccountPayableService;
import com.payable.service.AccountPayableServiceImpl;
 
@RestController
@RequestMapping("/acc_payable")
@CrossOrigin(origins="http://localhost:4200/")
public class AccountPayableController {
	
	@Autowired
	private AccountPayableServiceImpl accountPayableServiceImpl;
	
	@PostMapping("/createAccountPayable")
	public ResponseEntity<?> createPayable(@RequestBody AccountPayable accountPayable) {
		return accountPayableServiceImpl.createPayable(accountPayable);
	}
	
	@GetMapping("/getAllAccountPayable")
	public ResponseEntity<List<AccountPayable>> getAllAccountPayable() {
		return accountPayableServiceImpl.getAllAccountPayable();
	}
	
	
	@GetMapping("/getEntryByPayableId/{payableId}")
	public ResponseEntity<AccountPayable> getInvoiceById(@PathVariable long payableId) {
		return accountPayableServiceImpl.getInvoiceById(payableId);
	}
}