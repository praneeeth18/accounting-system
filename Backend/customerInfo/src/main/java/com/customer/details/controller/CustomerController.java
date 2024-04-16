package com.customer.details.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.details.exception.CustomerNotFoundException;
import com.customer.details.feign.UserServiceFeignInterface;
import com.customer.details.model.Customer;
import com.customer.details.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;
  private final UserServiceFeignInterface userServiceInterface;

  public CustomerController(CustomerService customerService, UserServiceFeignInterface userServiceInterface) {
    this.customerService = customerService;
    this.userServiceInterface = userServiceInterface;
    }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomer() {
    List<Customer> customers = customerService.getAllCustomer();
    return new ResponseEntity<>(customers, HttpStatus.OK);
    }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
    try {
      Customer customer = customerService.getCustomerById(id);
      return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  @PostMapping
  public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
      try {

          Customer existingCustomer = customerService.getCustomerByEmail(customer.getCustomerEmail());
          if (existingCustomer != null) {
              return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
          }

          ResponseEntity<?> companyResponse = userServiceInterface.getDetailsByCompanyId(customer.getCompanyId());
          if (companyResponse.getStatusCode() != HttpStatus.OK) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve company details");
          }

          Customer createdCustomer = customerService.createCustomer(customer);
          if (createdCustomer != null) {
              return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
          } else {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create customer");
          }
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
      }
  }


  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
    try {
      Customer updatedCustomer = customerService.updateCustomer(customer, id);
      return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
    try {
      customerService.deleteCustomer(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  @GetMapping("/getCustomerByCompanyId/{companyId}")
  public ResponseEntity<List<Customer>> getCustomerByCompanyId(@PathVariable int companyId) {
    return customerService.getCustomerByCompanyId(companyId);
    }
}
