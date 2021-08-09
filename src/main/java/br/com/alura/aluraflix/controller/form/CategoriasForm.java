package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.repository.CategoriasRepository;

public class CategoriasForm {

	@NotBlank
	private String titulo;
	@NotBlank
	private String cor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categoria converter() {
		return new Categoria(titulo, cor);
	}

	public Categoria atualizar(Long id, CategoriasRepository categoriasRepository) {
		Categoria categoria = categoriasRepository.getById(id);
		
		categoria.setTitulo(titulo);
		categoria.setCor(cor);
		
		return categoria;
	}

}
