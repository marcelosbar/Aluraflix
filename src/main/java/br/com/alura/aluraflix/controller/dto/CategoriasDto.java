package br.com.alura.aluraflix.controller.dto;

import org.springframework.data.domain.Page;

import br.com.alura.aluraflix.model.Categoria;
import lombok.Getter;
import lombok.Setter;

public class CategoriasDto {
	private @Getter Long id;
	private @Getter @Setter String titulo;
	private @Getter @Setter String cor;

	public CategoriasDto(Categoria categorias) {
		this.id = categorias.getId();
		this.titulo = categorias.getTitulo();
		this.cor = categorias.getCor();
	}

	public static Page<CategoriasDto> converter(Page<Categoria> listaDeCategorias) {
		return listaDeCategorias.map(CategoriasDto::new);
	}

}
