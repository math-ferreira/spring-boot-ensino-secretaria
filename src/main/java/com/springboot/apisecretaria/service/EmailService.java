package com.springboot.apisecretaria.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.apisecretaria.model.dto.EmailDTO;

@FeignClient(name = "email.client", url = "${feign.client.url.email}")
public interface EmailService {

	 @GetMapping("/check-email")
	 EmailDTO checkEmail(@RequestParam("email") String email);
}
