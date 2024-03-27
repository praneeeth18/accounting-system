package com.vendor.details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.vendor.details.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {


}
