package com.springboot.apisecretaria.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.apisecretaria.service.AutenticacaoService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	/*
	 * Recebe uma auth, metodo que serve para config a parte de autenticação,
	 * controle de acesso, login e etc
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuração de autorizações, como URL, quem acesso, perfil de acesso e etc - Irmeos utilizar em nosso projeto
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/alunos").permitAll()
		.antMatchers(HttpMethod.GET, "/alunos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	/*
	 * Configurações de recursos estaticos, como requisição de arquivos, js, css //
	 * imagens e etc - no nosso caso de backend não afz sentido, mas sim se fosse
	 * MVC
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
		System.out.println(cript.encode("senha123"));
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
