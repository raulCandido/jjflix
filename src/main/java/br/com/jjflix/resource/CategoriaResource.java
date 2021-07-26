package br.com.jjflix.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.jjflix.domain.Categoria;
import br.com.jjflix.domain.Video;
import br.com.jjflix.domain.dto.CategoriaDTO;
import br.com.jjflix.domain.dto.VideoDTO;
import br.com.jjflix.service.CategoriaService;
import br.com.jjflix.service.VideoService;
import br.com.jjflix.util.Util;

@RestController
@RequestMapping(value = "categorias/")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private VideoService videoService;
    
    @GetMapping
    public List<Categoria> pegarCategorias(){
	return categoriaService.buscarCategorias();
    }
    
    @GetMapping("{id}")
    public Categoria pegarCategoriaPorId(@PathVariable Long id){
	Categoria categoria = categoriaService.buscarCategoria(id);
	return categoria;
    }
    
    @GetMapping(value = "{id}/videos")
    public ResponseEntity<List<VideoDTO>> buscarVideoPorCategoria(@PathVariable Long id) {
	List<Video> videos = videoService.buscarVideosPorCategoria(id);
	
	List<VideoDTO> videosDto = new ArrayList<VideoDTO>();

	videos.forEach(e -> {
	    videosDto.add(new VideoDTO(e));
	});
	
	
	//videoService.converterVideoEmVideoDTO(video);
	return ResponseEntity.ok(videosDto);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, UriComponentsBuilder componentsBuilder){
	Categoria categoria = categoriaDTO.converter(categoriaDTO);
	categoriaService.inserirCatetegoria(categoria);
	URI uri = componentsBuilder.path("/{id}").buildAndExpand(categoria.getId()).toUri();
	return ResponseEntity.created(uri).body(categoria);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> editarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id){
	Categoria categoria = categoriaService.buscarCategoria(id);
	categoria.setTitulo(categoriaDTO.getTitulo());
	categoria.setCor(categoriaDTO.getCor());
	categoriaService.inserirCatetegoria(categoria);
	return ResponseEntity.ok(categoria);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarCategoriaPorId(@PathVariable Long id){
	Categoria categoria = categoriaService.buscarCategoria(id);
	categoriaService.deletarCategoria(categoria);
	return Util.createResponseEntity("Deletado com sucesso", HttpStatus.ACCEPTED);
    }
}
