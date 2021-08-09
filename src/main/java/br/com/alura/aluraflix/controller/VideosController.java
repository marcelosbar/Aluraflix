package br.com.alura.aluraflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.alura.aluraflix.controller.dto.VideosDto;
import br.com.alura.aluraflix.controller.form.VideosForm;
import br.com.alura.aluraflix.model.Categoria;
import br.com.alura.aluraflix.model.Video;
import br.com.alura.aluraflix.repository.CategoriasRepository;
import br.com.alura.aluraflix.repository.VideosRepository;

@RestController
@RequestMapping("/videos")
public class VideosController {

	@Autowired
	private VideosRepository videosRepository;
	
    @Autowired
    private CategoriasRepository categoriasRepository;
    
    @Autowired
    private ModelMapper modelMapper;

	@GetMapping
	public List<VideosDto> listaTodos(String search) {

		if (search == null || search.isBlank()) {
			List<Video> listaDeVideos = videosRepository.findAll();
			return VideosDto.converter(listaDeVideos);
		}
		// TODO Melhorar a busca para não ser case sensitive
		List<Video> listaDeVideos = videosRepository.findByTituloContaining(search);
		return VideosDto.converter(listaDeVideos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideosDto> listaUm(@PathVariable Long id) {
		Optional<Video> videos = videosRepository.findById(id);
		if (videos.isPresent()) {
			return ResponseEntity.ok(new VideosDto(videos.get()));
		}
		return ResponseEntity.notFound().build();
//		Poderia ser dessa forma caso quisesse enviar uma mensagem no corpo da resposta
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vídeo não encontrado!");
	}

	@PostMapping
	@Transactional
	public ResponseEntity<VideosDto> cadastrar(@RequestBody @Valid VideosForm form, UriComponentsBuilder uriBuilder) {
		//TODO É preciso tratar tentativa de cadastro de video com categoria inexistente!
		Video video = modelMapper.map(form, Video.class);
		
		Long idCategoria = form.getCategoria() == null ? 1L : form.getCategoria();
		Categoria categoria = categoriasRepository.getById(idCategoria);
		video.setCategoria(categoria);

		videosRepository.save(video);

		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideosDto(video));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideosDto> atualizar(@PathVariable Long id, @RequestBody @Valid VideosForm form) {
		Optional<Video> optional = videosRepository.findById(id);
		if (optional.isPresent()) {
			Video video = optional.get();
			video.atualizar(form.getTitulo(), form.getDescricao(), form.getUrl());
			return ResponseEntity.ok(new VideosDto(video));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Video> optional = videosRepository.findById(id);
		if (optional.isPresent()) {
			videosRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}