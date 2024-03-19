package com.payable.service;

import java.util.List;

import com.payable.model.AccountPayable;

public interface AccountPayableService {	
	List<AccountPayable> getAllAccountPayable();
	AccountPayable getAccountPayableById(Long payableId);
	AccountPayable createAccountPayable(AccountPayable accountPayable);
    void deleteAccountPayable(Long payableId);

}
