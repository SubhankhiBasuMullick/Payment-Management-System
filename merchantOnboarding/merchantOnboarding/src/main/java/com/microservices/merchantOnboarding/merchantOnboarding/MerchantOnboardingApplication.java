package com.microservices.merchantOnboarding.merchantOnboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.microservices.merchantOnboarding.merchantOnboarding.EntityModel")
public class MerchantOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantOnboardingApplication.class, args);
	}

}
