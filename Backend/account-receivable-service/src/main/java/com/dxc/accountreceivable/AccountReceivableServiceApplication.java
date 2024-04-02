package com.dxc.accountreceivable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AccountReceivableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountReceivableServiceApplication.class, args);
	}

}
