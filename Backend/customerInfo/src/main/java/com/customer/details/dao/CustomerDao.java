package com.customer.details.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.details.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>  {
	List<Customer> findByCompanyId(int companyId);
	Customer findByCustomerEmail(String email);
}
