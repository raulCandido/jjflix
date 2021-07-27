package br.com.jjflix.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria implements Serializable{

    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String titulo;
    private String cor;
   
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Video> videos;
    
    public Categoria() {
    }
    
    public Categoria(String titulo, String cor) {
	this.titulo = titulo;
	this.cor = cor;
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

    public String getCor() {
	return cor;
    }

    public void setCor(String cor) {
	this.cor = cor;
    }

    public List<Video> getVideos() {
	return videos;
    }

    public void setVideos(List<Video> videos) {
	this.videos = videos;
    }

    @Override
    public int hashCode() {
	return Objects.hash(cor, id, titulo, videos);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Categoria other = (Categoria) obj;
	return Objects.equals(cor, other.cor) && Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
		&& Objects.equals(videos, other.videos);
    }

}
