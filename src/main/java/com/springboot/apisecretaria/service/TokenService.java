package com.springboot.apisecretaria.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springboot.apisecretaria.api.config.security.form.LoginForm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${jwt.token.expiration}")
	private String expiration;
	
	@Value("${jwt.token.client.secret}")
	private String secret;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public String getToken(LoginForm loginForm) {
		UsernamePasswordAuthenticationToken user = loginForm.create();
		Authentication authentication = authenticationManager.authenticate(user);
		String token = createToken(authentication);
		return token;
	}
	
	private String createToken(Authentication authentication) {
		Long tempoExpiracao = new Date().getTime() + Long.parseLong(expiration); 
		Date dataExpiracao = new Date(tempoExpiracao);
		return Jwts.builder()
				.setIssuer("API Secretaria")
				.setSubject(authentication.getPrincipal().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
