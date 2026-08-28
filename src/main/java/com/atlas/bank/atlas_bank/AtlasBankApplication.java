package com.atlas.bank.atlas_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AtlasBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlasBankApplication.class, args);
	}

}