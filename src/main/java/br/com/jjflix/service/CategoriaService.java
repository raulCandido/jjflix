package br.com.jjflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjflix.domain.Categoria;
import br.com.jjflix.exception.ResourceNotFoundException;
import br.com.jjflix.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarCategorias() {
	return categoriaRepository.findAll();
    }

    public Categoria inserirCatetegoria(Categoria categoria) {
	return categoriaRepository.save(categoria);
    }

    public Categoria buscarCategoria(Long id) {
	Optional<Categoria> optional = categoriaRepository.findById(id);
	return optional.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada."));
    }

    public void deletarCategoria(Categoria categoria) {
	categoriaRepository.delete(categoria);

    }
}
