package com.project.sokoni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SokoniApplication {

	public static void main(String[] args) {
		SpringApplication.run(SokoniApplication.class, args);
	}

}
