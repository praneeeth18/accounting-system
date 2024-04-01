package com.vendor.details.service;

import java.util.List;
import com.vendor.details.entities.Vendor;

public interface VendorService {
	
	List<Vendor> getAllVendor();
	
	Vendor getVendorById(int vendorId);
	
	Vendor createVendor(Vendor vendor);
	
	Vendor updateVendor(Vendor vendor ,int vendorId);
	
	void deleteVendor(int vendorId);


}
