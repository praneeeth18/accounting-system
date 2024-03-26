package com.dxc.accountsReceivable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccountsReceivableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsReceivableServiceApplication.class, args);
	}

}
