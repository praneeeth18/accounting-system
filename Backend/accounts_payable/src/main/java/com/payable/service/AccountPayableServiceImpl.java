package com.payable.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payable.dao.AccountPayableDao;
import com.payable.model.AccountPayable;

@Service
public class AccountPayableServiceImpl implements AccountPayableService {
	@Autowired
	private AccountPayableDao accountPayableDao;
	

	@Override
	public List<AccountPayable> getAllAccountPayable() {
		return accountPayableDao.findAll();
	}

	@Override
	public AccountPayable getAccountPayableById(Long payableId) {
		return accountPayableDao.findById(payableId).orElse(null);
	}
		
	
	@Override
	public AccountPayable createAccountPayable(AccountPayable accountPayable) {
		
		if (accountPayable.getDate() == null) {
			accountPayable.setDate(LocalDate.now());
        }else if (accountPayable.getDate().isEmpty()) {
        	accountPayable.setDate(LocalDate.now());
        }
		
		accountPayable.setTotalAmount(accountPayable.getQuantity() * accountPayable.getPrice());
        return accountPayableDao.save(accountPayable);
	}

	@Override
	public void deleteAccountPayable(Long payableId) {
		accountPayableDao.deleteById(payableId);

	}

}
