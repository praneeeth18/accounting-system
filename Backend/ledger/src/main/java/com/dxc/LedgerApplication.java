package com.dxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LedgerApplication{

	public static void main(String[] args){
		SpringApplication.run(LedgerApplication.class, args);
		}

	@Bean
	protected RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
