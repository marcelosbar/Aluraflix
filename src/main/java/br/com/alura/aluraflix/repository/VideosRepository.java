package br.com.alura.aluraflix.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.model.Video;

public interface VideosRepository extends JpaRepository<Video, Long>{
	
	Page<Video> findByTituloContaining(String titulo, Pageable paginacao);

	Optional<Page<Video>> findByCategoria(Categoria categorias, Pageable paginacao);
	
}
