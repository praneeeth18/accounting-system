package com.payable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccountsPayableApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsPayableApplication.class, args);
	}

}
