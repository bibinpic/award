package com.award;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class AwardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwardApplication.class, args);
		ThreadContext.put("username", "bibin");
		log.trace("A TRACE Message");
		log.debug("A DEBUG Message");
		log.info("An INFO Message");
		log.warn("A WARN Message");
		log.error("An ERROR Message");
	}

}
