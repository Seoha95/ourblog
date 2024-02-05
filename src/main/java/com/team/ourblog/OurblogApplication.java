package com.team.ourblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EntityScan(basePackages = {"com.team.ourblog.entity"})
@SpringBootApplication
@EnableJpaAuditing
public class OurblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurblogApplication.class, args);
	}

}
