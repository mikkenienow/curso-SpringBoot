package com.mikke.minhasfinancas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mikke.minhasfinancas.exception.RegraDeNegocioException;
import com.mikke.minhasfinancas.model.entity.Usuario;
import com.mikke.minhasfinancas.model.repository.UsuarioRepository;
import com.mikke.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = this.repository.existsByEmail(email);
		if(existe){
			throw new RegraDeNegocioException("Este existe um cadastro com este e-mail!");
		}
		
	}

}
