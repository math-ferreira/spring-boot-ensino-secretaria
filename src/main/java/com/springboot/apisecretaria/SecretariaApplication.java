package com.springboot.apisecretaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecretariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretariaApplication.class, args);
	}

}
