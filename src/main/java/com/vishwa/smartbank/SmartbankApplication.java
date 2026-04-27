package com.vishwa.smartbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class SmartbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbankApplication.class, args);
	}

}
