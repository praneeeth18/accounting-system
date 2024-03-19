package com.customer.details.service;

import java.util.List;
import com.customer.details.model.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomer();

	public Customer createCustomer(Customer customer);

}
