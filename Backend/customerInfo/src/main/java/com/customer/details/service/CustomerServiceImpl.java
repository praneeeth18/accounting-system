package com.customer.details.service;

import java.util.List;

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
}
