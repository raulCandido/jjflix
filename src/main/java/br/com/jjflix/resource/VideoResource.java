package br.com.jjflix.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jjflix.domain.Categoria;
import br.com.jjflix.domain.Video;
import br.com.jjflix.domain.dto.VideoDTO;
import br.com.jjflix.service.CategoriaService;
import br.com.jjflix.service.VideoService;
import br.com.jjflix.util.Util;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<VideoDTO> buscarVideos() {
	List<Video> videos = videoService.buscarVideos();
	List<VideoDTO> videosDto = videos.stream().map(v -> new VideoDTO(v)).collect(Collectors.toList());

	return videosDto;
    }

    @GetMapping(params = "search")
    public List<VideoDTO> buscarVideosPorTitulo(@RequestParam(name = "search") String titulo) {
	List<Video> videos = videoService.buscarVideoPorTitulo(titulo);
	videoService.verificarListaVazia(videos);
	List<VideoDTO> videosDto = videos.stream().map(v -> new VideoDTO(v)).collect(Collectors.toList());
	return videosDto;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> buscarVideo(@PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	return ResponseEntity.ok(new VideoDTO(video));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<VideoDTO> inserirVideo(@Valid @RequestBody VideoDTO videoDTO,
	    UriComponentsBuilder componentsBuilder) {

	Categoria categoria = new Categoria();

	if (videoService.verificarCategoria(videoDTO)) {
	    categoria = categoriaService.buscarCategoria(1L);
	} else {
	    categoria = categoriaService.buscarCategoria(videoDTO.getCategoriaId());
	}
	Video video = videoDTO.converterVideoDtoEmVideo(videoDTO, categoria);

	video = videoService.inserirVideo(video);
	URI uri = componentsBuilder.path("/{id}").buildAndExpand(video.getId()).toUri();
	return ResponseEntity.created(uri).body(new VideoDTO(video));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> editarVideo(@Valid @RequestBody VideoDTO videoDTO, @PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	video.setTitulo(videoDTO.getTitulo());
	video.setDescricao(videoDTO.getDescricao());
	video.setUrl(videoDTO.getUrl());

	if (video.getCategoria().getId() != videoDTO.getCategoriaId()) {
	    Categoria categoria = categoriaService.buscarCategoria(videoDTO.getCategoriaId());
	    video.setCategoria(categoria);
	}

	video = videoService.inserirVideo(video);
	return ResponseEntity.ok(new VideoDTO(video));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarVideo(@PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	videoService.deletarVideo(video);
	return Util.createResponseEntity("Deletado com sucesso.", HttpStatus.ACCEPTED);
    }
}
