package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario buscarUsuarioPorId(Long id) {
		// Busca um usuário pelo ID. Se o usuário não for encontrado, lança uma exceção
		// do tipo RuntimeException.
		return this.usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

	}

	public Usuario salvarUsuario(Usuario usuario) {
		// Salva um usuário no banco de dados utilizando o repositório e retorna o
		// usuário salvo.
		return this.usuarioRepository.save(usuario);
	}

}
