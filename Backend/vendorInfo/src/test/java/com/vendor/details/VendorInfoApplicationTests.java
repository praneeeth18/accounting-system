package com.vendor.details;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vendor.details.entities.Vendor;
import com.vendor.details.repository.VendorRepository;
import com.vendor.details.service.VendorServiceImpl;

@SpringBootTest
class VendorInfoApplicationTests {

	@Test
	void getAllVendorTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		List<Vendor> expectedVendors = new ArrayList<>();
		when(vendorRepo.findAll()).thenReturn(expectedVendors);
		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);

		List<Vendor> actualVendors = vendorService.getAllVendor();

		assertEquals(expectedVendors, actualVendors);
	}

	@Test
	void createVendorTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		Vendor vendor = new Vendor(); // Create a Vendor object as per your requirements
		when(vendorRepo.save(any())).thenReturn(vendor);
		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);
		Vendor actualVendor = vendorService.createVendor(vendor);
		assertNotNull(actualVendor);
	}

	@Test
	void updateVendorTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		Vendor existingVendor = new Vendor(); // Create an existing Vendor object as per your requirements
		int vendorId = 1; // Define a vendorId for testing
		when(vendorRepo.findById(vendorId)).thenReturn(Optional.of(existingVendor));

		Vendor updatedVendor = new Vendor(); // Create an updated Vendor object as per your requirements
		updatedVendor.setVendorName("Updated Vendor Name");
		updatedVendor.setVendorEmail("updatedemail@example.com");

		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);

		Vendor actualVendor = vendorService.updateVendor(updatedVendor, vendorId);

		assertEquals(updatedVendor, actualVendor);
	}

	@Test
	void getVendorByIdTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		Vendor expectedVendor = new Vendor();
		int vendorId = 1; // Define a vendorId for testing
		when(vendorRepo.findById(vendorId)).thenReturn(Optional.of(expectedVendor));

		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);

		Vendor actualVendor = vendorService.getVendorById(vendorId);

		assertEquals(expectedVendor, actualVendor);
	}

	@Test
	void deleteVendorTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		int vendorId = 1; // Define a vendorId for testing
		when(vendorRepo.findById(vendorId)).thenReturn(Optional.of(new Vendor()));
		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);
		assertDoesNotThrow(() -> vendorService.deleteVendor(vendorId));

	}

	@Test
	void getVendorByCompanyIdTest() {
		VendorRepository vendorRepo = mock(VendorRepository.class);
		int companyId = 1; // Define a companyId for testing
		List<Vendor> expectedVendors = new ArrayList<>(); // Define expected vendors for testing
		when(vendorRepo.findByCompanyId(companyId)).thenReturn(expectedVendors);
		VendorServiceImpl vendorService = new VendorServiceImpl(vendorRepo);
		ResponseEntity<List<Vendor>> response = vendorService.getVendorByCompanyId(companyId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(expectedVendors, response.getBody());
	}

}
