package com.devs.travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TravelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApiApplication.class, args);
	}

}
