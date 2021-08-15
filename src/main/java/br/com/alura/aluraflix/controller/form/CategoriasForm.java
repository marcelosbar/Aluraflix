package br.com.alura.aluraflix.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.repository.CategoriasRepository;
import lombok.Getter;
import lombok.Setter;

public class CategoriasForm {

	@NotBlank
	private @Getter @Setter String titulo;
	@NotBlank
	private @Getter @Setter String cor;

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
