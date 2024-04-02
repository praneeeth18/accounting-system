package com.customer.details.service;

import java.util.List;
import com.customer.details.model.Customer;


public interface CustomerService {

	List<Customer> getAllCustomer();
	
	Customer getCustomerById(int customerId);
	
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer ,int customerId);
	
	void deleteCustomer(int customerId);

}
