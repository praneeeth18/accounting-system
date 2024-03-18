package com.vendor.details.repository;

import org.springframework.data.repository.CrudRepository;

import com.vendor.details.entities.Vendor;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
	

}
