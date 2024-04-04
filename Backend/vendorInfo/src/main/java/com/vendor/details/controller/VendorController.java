package com.vendor.details.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.details.entities.Vendor;
import com.vendor.details.exception.VendorNotFoundException;
import com.vendor.details.feign.UserServiceFeignInterface;
import com.vendor.details.service.VendorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/details")
public class VendorController {

    private final VendorService vendorService;
    private final UserServiceFeignInterface userServiceInterface;

    public VendorController(VendorService vendorService, UserServiceFeignInterface userServiceInterface) {
        this.vendorService = vendorService;
        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        var vendors = vendorService.getAllVendor(); 
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") int id) {
        try {
            var vendor = vendorService.getVendorById(id); 
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        }
        catch (VendorNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
      try {
      var companyResponse = userServiceInterface.getDetailsByCompanyId(vendor.getCompanyId()); 
            if (companyResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            var createdVendor = vendorService.createVendor(vendor); 
            return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
        }
      catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("id") int id, @RequestBody Vendor vendor) {
        try {
            var updatedVendor = vendorService.updateVendor(vendor, id); 
            return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
        } catch (VendorNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") int id) {
    	try {
            vendorService.deleteVendor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VendorNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @GetMapping("/getVendorByCompanyId/{companyId}")
    public ResponseEntity<List<Vendor>> getVendorByCompanyId(@PathVariable int companyId) {
        return vendorService.getVendorByCompanyId(companyId);
    }
}
