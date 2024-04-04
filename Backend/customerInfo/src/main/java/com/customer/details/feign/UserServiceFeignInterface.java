package com.customer.details.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceFeignInterface {
  
  @GetMapping("/user/getDetailsByCompanyId/{id}")
  ResponseEntity<Object> getDetailsByCompanyId(@PathVariable int id);
  
}
