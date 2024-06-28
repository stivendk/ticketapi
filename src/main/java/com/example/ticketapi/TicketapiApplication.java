package com.example.ticketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TicketapiApplication {

	public static void main(String[] args) {
		System.out.println("TicketapiApplication started");
		SpringApplication.run(TicketapiApplication.class, args);
	}

}
