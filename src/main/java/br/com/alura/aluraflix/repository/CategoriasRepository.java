package br.com.alura.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>{

}
