package com.remedios.gui.curso.usuario;

public record DadosDetalhamentoUsuario(Long id, String login) {
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getLogin());
	}
}