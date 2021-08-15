package br.com.alura.aluraflix.controller.dto;

import org.springframework.data.domain.Page;

import br.com.alura.aluraflix.model.Video;
import lombok.Getter;

public class VideosDto {

	private @Getter Long id;
	private @Getter String titulo;
	private @Getter String descricao;
	private @Getter String url;
	private @Getter Long categoria;

	public VideosDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoria = video.getCategoria() == null ? null : video.getCategoria().getId();
	}

	public static Page<VideosDto> converter(Page<Video> listaDeVideos) {
		return listaDeVideos.map(VideosDto::new);
	}
}
