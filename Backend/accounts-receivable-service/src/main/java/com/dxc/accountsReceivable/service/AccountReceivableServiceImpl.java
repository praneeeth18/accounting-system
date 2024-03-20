package com.dxc.accountsReceivable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dxc.accountsReceivable.model.AccountReceivable;
import com.dxc.accountsReceivable.repository.AccountReceivableRepository;

@Service
public class AccountReceivableServiceImpl implements AccountReceivableService{
	
	@Autowired
	private AccountReceivableRepository accountReceivableRepository;

	@Override
	public ResponseEntity<?> createReceivable(AccountReceivable accountReceivable) {
		try {
			accountReceivable.setAmount(accountReceivable.getQuantity() * accountReceivable.getPrice());
			accountReceivableRepository.save(accountReceivable);
			return ResponseEntity.status(HttpStatus.CREATED).body("Entry created!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating the entry!");
		}
	}

	@Override
	public ResponseEntity<List<AccountReceivable>> getAllAccountReceivable() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(accountReceivableRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
	}

}
