package com.team.ourblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@EntityScan(basePackages = {"com.team.ourblog.entity"})
@SpringBootApplication
public class OurblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurblogApplication.class, args);
	}

}
