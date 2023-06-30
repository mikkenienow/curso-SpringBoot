package com.mikke.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mikke.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveLancarErroAoValidarEmailJaCadastrado() {

			Usuario usuario = Usuario.builder().nome("usuario").email("Usuario@email.com").senha("123456").build();
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getNome());
			repository.save(usuario);
			boolean result = repository.existsByEmail("Usuario@email.com");
			Assertions.assertThat(result).isTrue();
			
	}
}
