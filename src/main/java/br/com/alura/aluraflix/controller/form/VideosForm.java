package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

public class VideosForm {

	@NotBlank
	private @Getter @Setter String titulo;
	@NotBlank
	private @Getter @Setter String descricao;
	@NotBlank
	@URL
	private @Getter @Setter String url;
	private @Getter @Setter Long categoria;

}
