package com.bloodApp.BDS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"com.bloodApp.BDS.controller, com.bloodAppBDS.services"})
//@EntityScan("com.bloodApp.BDS.model")
//@EnableJpaRepositories("com.bloodApp.BDS.repository")
public class BdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdsApplication.class, args);
	}

}
