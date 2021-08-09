package br.com.alura.aluraflix.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.aluraflix.model.Video;

public class VideosDto {

	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Long categoria;

	public VideosDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoria = video.getCategoria() == null ? null : video.getCategoria().getId();
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}
	
	public Long getCategoria() {
		return categoria;
	}

	public static List<VideosDto> converter(List<Video> listaDeVideos) {
		return listaDeVideos.stream().map(VideosDto::new).collect(Collectors.toList());
	}
}
