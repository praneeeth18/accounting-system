package com.vendor.details.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.details.entities.Vendor;
import com.vendor.details.exception.VendorNotFoundException;
import com.vendor.details.repository.VendorRepository;
@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public List<Vendor> getAllVendor() {
		return (List<Vendor>) vendorRepo.findAll();
	}

	@Override
	public Vendor createVendor(Vendor vendor) {
		return vendorRepo.save(vendor);
	}

	@Override
	public Vendor updateVendor(Vendor vendor, int vendorId) {
		Optional<Vendor> optionalexistingVendor = vendorRepo.findById(vendorId);

		if (optionalexistingVendor.isPresent()) {

			Vendor existingVendor = optionalexistingVendor.get();
			existingVendor.setVendorName(vendor.getVendorName());
			existingVendor.setVendorEmail(vendor.getVendorEmail());
			vendorRepo.save(existingVendor);
			return existingVendor;
		} else {
			throw new VendorNotFoundException("Vendor with ID " + vendorId + " not found");

		}
	}

	@Override
	public Vendor getVendorById(int vendorId) {
		Optional<Vendor> optionalVendor = vendorRepo.findById(vendorId);
		if (optionalVendor.isPresent()) {
			return optionalVendor.get();
		} else {
			throw new VendorNotFoundException("Vendor with ID " + vendorId + " not found");
		}
	}

	@Override
	public void deleteVendor(int vendorId) {
		Optional<Vendor> optionalVendor = vendorRepo.findById(vendorId);
		if (optionalVendor.isPresent()) {
			vendorRepo.deleteById(vendorId);
		} else {
			throw new VendorNotFoundException("Vendor with ID " + vendorId + " not found");
		}
	}

}
