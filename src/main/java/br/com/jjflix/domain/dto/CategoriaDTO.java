package br.com.jjflix.domain.dto;

import javax.validation.constraints.NotEmpty;

import br.com.jjflix.domain.Categoria;

public class CategoriaDTO {

    private Long id;    
    @NotEmpty(message = "Campo Título obrigatório.")
    private String titulo;
    @NotEmpty(message = "Campo Cor obrigatório.")
    private String cor;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitulo() {
	return titulo;
    }

    public String getCor() {
	return cor;
    }

    public Categoria converter(CategoriaDTO categoriaDTO) {
	Categoria categoria = new Categoria(categoriaDTO.getTitulo(), categoriaDTO.getCor());
	return categoria;
    }

}
