package com.mikke.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.mikke.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveLancarErroAoValidarEmailJaCadastrado() {
		//cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("Usuario@email.com").senha("123456").build();
		repository.save(usuario);
		
		//ação / execução
		boolean result = repository.existsByEmail("Usuario@email.com");
		
		//verificação
		Assertions.assertThat(result).isTrue();
			
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail(){
		//cenario
		repository.deleteAll();
		
		//acao
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
	}
}
