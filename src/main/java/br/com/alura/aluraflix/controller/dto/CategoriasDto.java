package br.com.alura.aluraflix.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.aluraflix.model.Categoria;

public class CategoriasDto {
	private Long id;
	private String titulo;
	private String cor;

	public CategoriasDto(Categoria categorias) {
		this.id = categorias.getId();
		this.titulo = categorias.getTitulo();
		this.cor = categorias.getCor();
	}

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

	public Long getId() {
		return id;
	}

	public static List<CategoriasDto> converter(List<Categoria> listaDeCategorias) {
		return listaDeCategorias.stream().map(CategoriasDto::new).collect(Collectors.toList());
	}

}
