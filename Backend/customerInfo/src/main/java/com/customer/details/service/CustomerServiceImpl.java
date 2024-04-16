package com.customer.details.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.details.dao.CustomerDao;
import com.customer.details.exception.CustomerNotFoundException;
import com.customer.details.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerDao customerDao;

    // Constructor
  public CustomerServiceImpl(CustomerDao customerDao) {
    this.customerDao = customerDao;
    }

  @Override
  public List<Customer> getAllCustomer() {
    return customerDao.findAll();
    }

  @Override
  public Customer createCustomer(Customer customer) {
    return customerDao.save(customer);
    }

  @Override
  public Customer updateCustomer(Customer customer, int customerId) {
    var optionalExistingCustomer = customerDao.findById(customerId);

    if (optionalExistingCustomer.isPresent()) {
      var existingCustomer = optionalExistingCustomer.get();
      existingCustomer.setCustomerName(customer.getCustomerName());
      existingCustomer.setCustomerEmail(customer.getCustomerEmail());
      return customerDao.save(existingCustomer);
        } else {
      throw new CustomerNotFoundException(getNotFoundExceptionMessage(customerId));
        }
    }

  @Override
  public Customer getCustomerById(int customerId) {
    var optionalCustomer = customerDao.findById(customerId);
    if (optionalCustomer.isPresent()) {
      return optionalCustomer.get();
        } else {
      throw new CustomerNotFoundException(getNotFoundExceptionMessage(customerId));
        }
    }

  @Override
  public void deleteCustomer(int customerId) {
    var optionalCustomer = customerDao.findById(customerId);
    if (optionalCustomer.isPresent()) {
      customerDao.deleteById(customerId);
        } else {
      throw new CustomerNotFoundException(getNotFoundExceptionMessage(customerId));
        }
    }

    // Utility method to get the "not found" message
  private static String getNotFoundExceptionMessage(int customerId) {
    return "Customer with ID " + customerId + " not found";
    }
  
  @Override
  public Customer getCustomerByEmail(String email) {
	    return customerDao.findByCustomerEmail(email);
	}

    
  @Override
  public ResponseEntity<List<Customer>> getCustomerByCompanyId(int companyId) {
    try {
      List<Customer> vendors = customerDao.findByCompanyId(companyId);
      if(vendors.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
      return ResponseEntity.status(HttpStatus.OK).body(vendors);
        } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
}
