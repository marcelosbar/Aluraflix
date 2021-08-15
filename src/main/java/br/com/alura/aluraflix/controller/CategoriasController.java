package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.aluraflix.controller.dto.CategoriasDto;
import br.com.alura.aluraflix.controller.form.CategoriasForm;
import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.model.Video;
import br.com.alura.aluraflix.repository.CategoriasRepository;
import br.com.alura.aluraflix.repository.VideosRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private CategoriasRepository categoriasRepository;

	@Autowired
	private VideosRepository videosRepository;

	@GetMapping
	public Page<CategoriasDto> listarTodos(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {
		Page<Categoria> listaDeCategorias = categoriasRepository.findAll(paginacao);
		return CategoriasDto.converter(listaDeCategorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriasDto> listarUmPorId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriasRepository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriasDto(categoria.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<Page<Video>> listarVideosPorCategoria(@PathVariable Long id,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {
		Optional<Categoria> categoria = categoriasRepository.findById(id);

		if (categoria.isPresent()) {
			Optional<Page<Video>> videos = videosRepository.findByCategoria(categoria.get(), paginacao);
			if (videos.isPresent()) {
				return ResponseEntity.ok(videos.get());
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriasDto> cadastrar(@RequestBody @Valid CategoriasForm form,
			UriComponentsBuilder uriBuilder) {
		Categoria categorias = form.converter();
		categoriasRepository.save(categorias);

		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categorias.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriasDto(categorias));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriasDto> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriasForm form) {
		Optional<Categoria> optional = categoriasRepository.findById(id);
		if (optional.isPresent()) {
			Categoria categorias = form.atualizar(id, categoriasRepository);
			return ResponseEntity.ok(new CategoriasDto(categorias));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		// TODO Necessário tratar tentativa de exclusão de categoria que possui videos
		// cadastrados!
		Optional<Categoria> optional = categoriasRepository.findById(id);
		if (optional.isPresent()) {
			categoriasRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
