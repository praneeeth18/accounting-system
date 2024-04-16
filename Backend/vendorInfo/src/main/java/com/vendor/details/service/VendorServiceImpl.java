package com.vendor.details.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vendor.details.entities.Vendor;
import com.vendor.details.exception.VendorNotFoundException;
import com.vendor.details.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
    
  private VendorRepository vendorRepo;

    // Constructor
  public VendorServiceImpl(VendorRepository vendorRepo) {
    this.vendorRepo = vendorRepo;
    }

  @Override
  public List<Vendor> getAllVendor() {
    return vendorRepo.findAll();
    }

  @Override
  public Vendor createVendor(Vendor vendor) {
    return vendorRepo.save(vendor);
    }

  @Override
  public Vendor updateVendor(Vendor vendor, int vendorId) {
    var optionalExistingVendor = vendorRepo.findById(vendorId);

    if (optionalExistingVendor.isPresent()) {
      var existingVendor = optionalExistingVendor.get();
      existingVendor.setVendorName(vendor.getVendorName());
      existingVendor.setVendorEmail(vendor.getVendorEmail());
      vendorRepo.save(existingVendor);
      return existingVendor;
      } else {
      throw new VendorNotFoundException(getNotFoundExceptionMessage(vendorId));
        }
    }

  @Override
  public Vendor getVendorById(int vendorId) {
    var optionalVendor = vendorRepo.findById(vendorId);
    if (optionalVendor.isPresent()) {
      return optionalVendor.get();
        } else {
      throw new VendorNotFoundException(getNotFoundExceptionMessage(vendorId));
        }
    }

  @Override
  public void deleteVendor(int vendorId) {
    var optionalVendor = vendorRepo.findById(vendorId);
    if (optionalVendor.isPresent()) {
      vendorRepo.deleteById(vendorId);
        } else {
      throw new VendorNotFoundException(getNotFoundExceptionMessage(vendorId));
        }
    }

    // Utility method to get the "not found" message
  private static String getNotFoundExceptionMessage(int vendorId) {
    return "Vendor with ID " + vendorId + " not found";
    }
    
  @Override
  public ResponseEntity<List<Vendor>> getVendorByCompanyId(int companyId) {
    try {
      List<Vendor> vendors = vendorRepo.findByCompanyId(companyId);
      if (vendors.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
      }
      return ResponseEntity.status(HttpStatus.OK).body(vendors);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

	@Override
	public Vendor getVendorByEmail(String email) {
		return vendorRepo.findByVendorEmail(email);
	}

}
