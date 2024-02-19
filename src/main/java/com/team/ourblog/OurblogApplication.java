package com.team.ourblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class OurblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurblogApplication.class, args);
	}

}
