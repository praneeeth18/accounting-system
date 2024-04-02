package com.customer.details.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.details.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>  {
	
}
