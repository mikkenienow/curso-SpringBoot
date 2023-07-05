package com.mikke.minhasfinancas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.mikke.minhasfinancas.exception.RegraDeNegocioException;
import com.mikke.minhasfinancas.model.repository.UsuarioRepository;
import com.mikke.minhasfinancas.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	UsuarioService service;
	
	UsuarioRepository repository;
	
	@Before
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarEmail() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		service.validarEmail("usuario@email.com");
		
		//verificação
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
