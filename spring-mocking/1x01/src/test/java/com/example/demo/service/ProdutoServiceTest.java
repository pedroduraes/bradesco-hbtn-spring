package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private ProdutoService produtoService;

	@Test
	void deveRetornarProdutoQuandoIdExistir() {
		// implemente
		Optional<Produto> p = Optional.of(new Produto(1L, "camisa", 100.0));
		Mockito.when(this.produtoRepository.findById(1L)).thenReturn(p);
		Produto result = this.produtoService.buscarPorId(1L);
		assertNotNull(result);
		assertEquals(p.get(), result);
	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		Mockito.when(this.produtoRepository.findById(1L)).thenThrow(new RuntimeException("Produto não encontrado"));
		assertThrows(RuntimeException.class, () -> this.produtoService.buscarPorId(1L));
	}
}