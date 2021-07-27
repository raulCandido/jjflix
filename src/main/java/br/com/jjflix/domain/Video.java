package br.com.jjflix.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Video implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaid")
    private Categoria categoria;
    
    public Video() {
    }

    public Video(String titulo, String descricao, String url, Categoria categoria) {
	super();
	this.titulo = titulo;
	this.descricao = descricao;
	this.url = url;
	this.categoria = categoria;
    }
    

    public Categoria getCategoria() {
	return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
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
