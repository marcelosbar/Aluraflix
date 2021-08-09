package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public class VideosForm {

	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotBlank
	@URL
	private String url;
	private Long categoria;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

}
