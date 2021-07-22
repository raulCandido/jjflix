package br.com.jjflix.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Campo obrigatório")
	private String titulo;
	@NotEmpty(message = "Campo obrigatório")
	private String descricao;
	@NotEmpty(message = "Campo obrigatório")
	private String url;
	
	
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
	
	@Override
	public int hashCode() {
	    return Objects.hash(descricao, id, titulo, url);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Video other = (Video) obj;
	    return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
		    && Objects.equals(titulo, other.titulo) && Objects.equals(url, other.url);
	}
	
	
	
}
