package com.example.accountvalidation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.accountvalidation.controller.AccountController;
import com.example.accountvalidation.domain.ValidationRequest;

@SpringBootTest
class AccountvalidationApplicationTests {
	@Autowired
	AccountController accountController;

	@Test
	void contextLoads() {
	}

	@Test
	void testAccountValidation() {
		List<String> sources = new ArrayList<String>();
		sources.add("testSource1");
		sources.add("testSource2");
		ValidationRequest validationRequest = new ValidationRequest();
		validationRequest.setAccountNumber("12345678");
		validationRequest.setSources(sources);
		System.out.println(accountController.validate(validationRequest).getResult());
	}
	
}
