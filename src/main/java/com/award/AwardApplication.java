package com.award;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AwardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwardApplication.class, args);
	}

}
