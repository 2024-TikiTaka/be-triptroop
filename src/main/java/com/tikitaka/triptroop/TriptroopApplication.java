package com.tikitaka.triptroop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TriptroopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriptroopApplication.class, args);
	}

}
