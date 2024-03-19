package com.customer.details.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.details.dao.CustomerDao;
import com.customer.details.model.Customer;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.save(customer) ;
	}

	
}
