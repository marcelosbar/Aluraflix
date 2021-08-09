package br.com.alura.aluraflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.model.Video;

public interface VideosRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTituloContaining(String titulo);

	Optional<List<Video>> findByCategoria(Categoria categorias);
	
}
