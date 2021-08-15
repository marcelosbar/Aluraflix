package br.com.alura.aluraflix.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErroDeValidacaoDto {

	private @Getter String campo;
	private @Getter String erro;

}
