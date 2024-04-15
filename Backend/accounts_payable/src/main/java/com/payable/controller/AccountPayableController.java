package com.payable.controller;
 
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.payable.model.AccountPayable;
import com.payable.service.AccountPayableServiceImpl;
 
@RestController
@RequestMapping("/accountPayable")
public class AccountPayableController {
	
	private final AccountPayableServiceImpl accountPayableServiceImpl;
	
	public AccountPayableController(AccountPayableServiceImpl accountPayableServiceImpl) {
        this.accountPayableServiceImpl = accountPayableServiceImpl;
    }
	
	@PostMapping("/createAccountPayable")
	public ResponseEntity<String> createPayable(@RequestBody AccountPayable accountPayable) {
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
	
	@GetMapping("/getEntryByCompanyId/{companyId}")
	public ResponseEntity<List<AccountPayable>> getEntryByCompanyId(@PathVariable int companyId) {
		return accountPayableServiceImpl.findByCompanyId(companyId);
	}	
	
	@PutMapping("/updatePayable/{payableId}")
	public ResponseEntity<String> updatePayable(@PathVariable Long payableId, @RequestBody AccountPayable updatedPayable) {
	        return accountPayableServiceImpl.updatePayable(payableId, updatedPayable);
	}
}