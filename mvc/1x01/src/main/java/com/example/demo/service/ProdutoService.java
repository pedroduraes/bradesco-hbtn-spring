package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;

@Service
public class ProdutoService {
	private List<Produto> produtos = new ArrayList<>();
	private Long contadorId = 1L;

	public List<Produto> listarProdutos() {
		return this.produtos;
	}

	public Produto adicionarProduto(Produto produto) {
		produto.setId(contadorId);
		this.produtos.add(produto);
		contadorId+=1;
		return produto;
	}

	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
		Optional<Produto> prod = this.produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (prod.isPresent()) {

			prod.get().setNome(produtoAtualizado.getNome());
			prod.get().setPreco(produtoAtualizado.getPreco());

			return prod.get();
		}

		return null;

	}

	public boolean deletarProduto(Long id) {
		Optional<Produto> prod = this.produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (prod.isPresent()) {
			contadorId-=1;
			return this.produtos.remove(prod.get());
		}
		return false;

	}
}