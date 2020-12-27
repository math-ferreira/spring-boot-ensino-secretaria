package com.springboot.apisecretaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.apisecretaria.model.Usuario;
import com.springboot.apisecretaria.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Nao foi possivel se auenticar com esse usuario");
	}

}
