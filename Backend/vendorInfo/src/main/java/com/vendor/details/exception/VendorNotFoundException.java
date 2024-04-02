package com.vendor.details.exception;

public class VendorNotFoundException extends RuntimeException {
  
  public VendorNotFoundException(String message) {
    
    super(message);
  }
}
