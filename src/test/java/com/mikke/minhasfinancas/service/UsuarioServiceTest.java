package com.mikke.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.mikke.minhasfinancas.exception.RegraDeNegocioException;
import com.mikke.minhasfinancas.model.entity.Usuario;
import com.mikke.minhasfinancas.model.repository.UsuarioRepository;
import com.mikke.minhasfinancas.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Before
	public void setUp() {
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		service.validarEmail("usuario@email.com");
		
		//verificação
	}
	
	@Test(expected = Test.None.class)
	public void deveAutenticarUmUsuarioComSucesso() {
		//cenario
		String email = "usuario@email.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//ação
		Usuario result = service.autenticar(email, senha);
		
		//verificação
		
		Assertions.assertThat(result).isNotNull();
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void deveLancarErroAoValidarEmailQuandoJaCadastrado() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		//ação
		service.validarEmail("usuario@email.com");
		
		//verificação
	}
}
