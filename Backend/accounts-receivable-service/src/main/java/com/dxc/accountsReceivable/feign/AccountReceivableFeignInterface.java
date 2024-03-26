package com.dxc.accountsReceivable.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface AccountReceivableFeignInterface {
	
	@GetMapping("/user/getDetailsByCompanyId/{id}")
	public ResponseEntity<?> getDetailsByCompanyId(@PathVariable int id);
}
