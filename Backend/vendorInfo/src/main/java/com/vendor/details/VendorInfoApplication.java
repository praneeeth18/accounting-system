package com.vendor.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class VendorInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorInfoApplication.class, args);
	}

}
