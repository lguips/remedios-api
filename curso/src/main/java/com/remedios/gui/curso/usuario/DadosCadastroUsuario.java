package com.remedios.gui.curso.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
		@NotBlank
		String login,
		
		@NotBlank
		String senha
		) {

}
