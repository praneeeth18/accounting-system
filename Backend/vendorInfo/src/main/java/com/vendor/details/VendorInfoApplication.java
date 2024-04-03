package com.vendor.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableFeignClients
public class VendorInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorInfoApplication.class, args);
	}

}
