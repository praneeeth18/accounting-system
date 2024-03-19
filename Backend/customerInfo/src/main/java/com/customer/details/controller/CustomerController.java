package com.customer.details.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.details.model.Customer;
import com.customer.details.service.CustomerService;

@RestController
@RequestMapping
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomer();
	}
	
	
    @PostMapping("/create")
	public Customer createCustomer( @RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	
	}
}
