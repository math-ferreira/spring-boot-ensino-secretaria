package com.springboot.apisecretaria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecretariaApplication {

    private static Logger logger = LoggerFactory.getLogger(SecretariaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecretariaApplication.class, args);

        logger.info("Aplicacao iniciada");

    }

}
