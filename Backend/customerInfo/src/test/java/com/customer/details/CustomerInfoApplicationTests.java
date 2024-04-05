package com.customer.details;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customer.details.dao.CustomerDao;
import com.customer.details.model.Customer;
import com.customer.details.service.CustomerServiceImpl;

@SpringBootTest
class CustomerInfoApplicationTests {

    @Test
    void getAllCustomerTest() {
        CustomerDao customerDao = mock(CustomerDao.class);
        List<Customer> expectedCustomers = new ArrayList<>();
        when(customerDao.findAll()).thenReturn(expectedCustomers);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        
        List<Customer> actualCustomers = customerService.getAllCustomer();
        
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    void createCustomerTest() {
        CustomerDao customerDao = mock(CustomerDao.class);
        Customer customer = new Customer(); 
        when(customerDao.save(any())).thenReturn(customer);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        
        Customer actualCustomer = customerService.createCustomer(customer);
        
        assertNotNull(actualCustomer);
    }
    
	/*
	 * @Test void updateCustomerTest() { CustomerDao customerDao =
	 * mock(CustomerDao.class); Customer existingCustomer = new Customer(); int
	 * customerId = 1;
	 * when(customerDao.findById(customerId)).thenReturn(Optional.of(
	 * existingCustomer));
	 * 
	 * Customer updatedCustomer = new Customer();
	 * updatedCustomer.setCustomerName(existingCustomer.getCustomerName());
	 * updatedCustomer.setCustomerEmail(existingCustomer.getCustomerEmail());
	 * 
	 * CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
	 * 
	 * Customer actualCustomer = customerService.updateCustomer(updatedCustomer,
	 * customerId);
	 * 
	 * assertEquals(updatedCustomer, actualCustomer); }
	 */
    
    @Test
    void getCustomerByIdTest() {
        CustomerDao customerDao = mock(CustomerDao.class);
        Customer expectedCustomer = new Customer(); 
        int customerId = 1;
        when(customerDao.findById(customerId)).thenReturn(Optional.of(expectedCustomer));
        
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        
        Customer actualCustomer = customerService.getCustomerById(customerId);
        
        assertEquals(expectedCustomer, actualCustomer);
    }
    
    @Test
    void deleteCustomerTest() {
        CustomerDao customerDao = mock(CustomerDao.class);
        int customerId = 1; 
        when(customerDao.findById(customerId)).thenReturn(Optional.of(new Customer()));
        
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        
        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));
        
    }
    
    @Test
    void getCustomerByCompanyIdTest() {
        CustomerDao customerDao = mock(CustomerDao.class);
        int companyId = 1; 
        List<Customer> expectedCustomers = new ArrayList<>(); // Define expected customers for testing
        when(customerDao.findByCompanyId(companyId)).thenReturn(expectedCustomers);
        
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDao);
        
        ResponseEntity<List<Customer>> response = customerService.getCustomerByCompanyId(companyId);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedCustomers, response.getBody());
    }

}
