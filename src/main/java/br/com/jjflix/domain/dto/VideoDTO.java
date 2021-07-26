package br.com.jjflix.domain.dto;

import javax.validation.constraints.NotEmpty;

import br.com.jjflix.domain.Categoria;
import br.com.jjflix.domain.Video;

public class VideoDTO {

    private Long id;
    @NotEmpty(message = "Campo de Título é obrigatório.")
    private String titulo;
    @NotEmpty(message = "Campo de Descrição é obrigatório.")
    private String descricao;
    @NotEmpty(message = "Campo de URL é obrigatório.")
    private String url;

    private Long categoriaId;

    public Video converterVideoDtoEmVideo(VideoDTO videoDTO, Categoria categoria) {
	return new Video(
		videoDTO.getTitulo(),
		videoDTO.getDescricao(),
		videoDTO.getUrl(),
		categoria);
    }

    public VideoDTO(Long id, String titulo, String descricao, String url, Long categoriaId) {
	this.id = id;
	this.titulo = titulo;
	this.descricao = descricao;
	this.url = url;
	this.categoriaId = categoriaId;
    }
    
    public VideoDTO(Video video) {
	this.id = video.getId();
	this.titulo = video.getTitulo();
	this.descricao = video.getDescricao();
	this.url = video.getUrl();
	this.categoriaId = video.getCategoria().getId();

    }
    

    public Long getCategoriaId() {
	return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
	this.categoriaId = categoriaId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

}
