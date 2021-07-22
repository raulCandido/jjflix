package br.com.jjflix.resource;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jjflix.domain.Video;
import br.com.jjflix.service.VideoService;
import br.com.jjflix.util.Util;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public List<Video> buscarVideos() {
	List<Video> videos = videoService.buscarVideos();
	return videos;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarVideo(@PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	return ResponseEntity.ok(video);
    }

    @PostMapping()
    public ResponseEntity<Void> inserirVideo(@Valid @RequestBody Video video) {
	video = videoService.inserirVideo(video);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(video.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarVideo(@Valid @RequestBody Video novoVideo, @PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	video.setTitulo(novoVideo.getTitulo());
	video.setDescricao(novoVideo.getDescricao());
	video.setUrl(novoVideo.getUrl());
	videoService.inserirVideo(video);
	return ResponseEntity.ok(video);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarVideo(@PathVariable Long id) {
	Video video = videoService.buscarVideoPorId(id);
	videoService.deletarVideo(video);
	return Util.createResponseEntity("Deletado com sucesso.", HttpStatus.ACCEPTED);
    }
}
