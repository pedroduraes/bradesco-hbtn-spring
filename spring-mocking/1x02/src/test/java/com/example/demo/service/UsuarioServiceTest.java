package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioService usuarioService;

	@BeforeEach
	public void setUp() {
		// With openMocks, the testing process automatically initializes all fields that
		// use @Mock, @Spy, @Captor, or @InjectMocks annotations while scanning test
		// class code.
		MockitoAnnotations.openMocks(this);

	}

	/*
	 * deveRetornarUsuarioQuandoIdExistir(): Testa a busca de um usuário existente,
	 * verificando se o método findById() retorna o objeto esperado.
	 * deveLancarExcecaoQuandoUsuarioNaoExistir(): Testa se a exceção correta é
	 * lançada quando um usuário não é encontrado. 
	 * deveSalvarUsuarioComSucesso(): Testa a persistência de um usuário e verifica se os dados retornados são os
	 * esperados.
	 */

	@Test
	public void deveRetornarUsuarioQuandoIdExistir() {
		Optional<Usuario> usuario = Optional.of(new Usuario(1L, "Mario", "mario@nintendo.com.br"));
		Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(usuario);
		Usuario resultado = this.usuarioService.buscarUsuarioPorId(1L);
		assertNotNull(resultado);
		assertEquals(usuario.get(), resultado);
	}

	@Test
	public void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		Mockito.when(this.usuarioRepository.findById(Mockito.anyLong()))
				.thenThrow(new RuntimeException("Usuario nao encontrado"));
		assertThrows(RuntimeException.class, () -> this.usuarioService.buscarUsuarioPorId(Mockito.anyLong()));
	}
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		Usuario usuario = new Usuario(null, "Mario", "mario@nintendo.com.br");
		Mockito.when(this.usuarioRepository.save(Mockito.any())).thenReturn(usuario);
		Usuario resultado = this.usuarioService.salvarUsuario(usuario);
		assertEquals(usuario, resultado);
	}
}
