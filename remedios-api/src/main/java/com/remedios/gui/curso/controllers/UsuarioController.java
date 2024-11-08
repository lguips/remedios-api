package com.remedios.gui.curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.gui.curso.usuario.DadosCadastroUsuario;
import com.remedios.gui.curso.usuario.DadosDetalhamentoUsuario;
import com.remedios.gui.curso.usuario.Usuario;
import com.remedios.gui.curso.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados,
			UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados);
		
		repository.save(usuario);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario.getId(), usuario.getLogin()));
	}
}
