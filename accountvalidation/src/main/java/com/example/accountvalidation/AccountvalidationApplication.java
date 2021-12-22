package com.example.accountvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AccountvalidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountvalidationApplication.class, args);
	}

}
