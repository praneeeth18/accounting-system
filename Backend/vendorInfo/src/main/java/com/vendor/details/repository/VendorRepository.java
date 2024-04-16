package com.vendor.details.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendor.details.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	List<Vendor> findByCompanyId(int companyId);
	Vendor findByVendorEmail(String email);
}
