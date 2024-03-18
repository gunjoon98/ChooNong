package com.ssafy.choonong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ssafy.choonong.entity")
public class ChoonongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoonongApplication.class, args);
	}

}
