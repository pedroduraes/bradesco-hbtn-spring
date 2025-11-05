package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public List<Produto> listarProdutos() {
		return this.produtoService.listarProdutos();
	}

	@PostMapping
	public Produto adicionarProduto(@RequestBody Produto produto) {
		return this.produtoService.adicionarProduto(produto);
	}

	@PutMapping("/{id}")
	public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		return this.produtoService.atualizarProduto(id, produto);
	}

	@DeleteMapping("/{id}")
	public String deletarProduto(@PathVariable Long id) {
		return this.produtoService.deletarProduto(id) ? "Sucesso" : "Falha";
	}
}