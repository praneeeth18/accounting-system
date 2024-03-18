package com.vendor.details.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.details.entities.Vendor;
import com.vendor.details.repository.VendorRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/details")
public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@GetMapping
	public Iterable<Vendor> getVendors(){
		return vendorRepository.findAll();	
	}
	
	@PostMapping
	public Vendor create(@RequestBody Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	@GetMapping("/{vendorId}")
	public Optional<Vendor> getVendor(@PathVariable int vendorId) {
		return vendorRepository.findById(vendorId);
	}
	
	

}
