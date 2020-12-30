package com.springboot.apisecretaria.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springboot.apisecretaria.api.config.security.form.LoginForm;
import com.springboot.apisecretaria.model.Usuario;

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
	
	
	public String transformDataToToken(LoginForm loginForm) {
		UsernamePasswordAuthenticationToken user = loginForm.create();
		Authentication authentication = authenticationManager.authenticate(user);
		String token = createToken(authentication);
		return token;
	}
	
	private String createToken(Authentication authentication) {
		Long tempoExpiracao = new Date().getTime() + Long.parseLong(expiration); 
		Date dataExpiracao = new Date(tempoExpiracao);
		Usuario usuario = (Usuario) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("API Secretaria")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}
	
	public boolean isValid(String token) {
		
		 try {
			Jwts.parser()
			 	.setSigningKey(this.secret)
				.parseClaimsJws(token);
			 return true;
		} catch (Exception ex) {
			return false;
		}	
	}
	
	public Long getIdUsuarioToken(String token) {
		return Long.parseLong(
				Jwts.parser()
			 		.setSigningKey(this.secret)
			 		.parseClaimsJws(token)
			 		.getBody()
			 		.getSubject());
	}
}
