package com.dxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LedgerApplication{

	public static void main(String[] args){
		SpringApplication.run(LedgerApplication.class, args);
		}

}
