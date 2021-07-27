package br.com.jjflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjflix.domain.Video;
import br.com.jjflix.domain.dto.VideoDTO;
import br.com.jjflix.exception.ResourceNotFoundException;
import br.com.jjflix.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    

    public List<Video> buscarVideos() {
	return videoRepository.findAll();
    }
    
    public List<Video> buscarVideosPorCategoria(Long id) {
 	return videoRepository.findVideosByCategoriaId(id);
     }

    public Video buscarVideoPorId(Long id) {
	Optional<Video> optional = videoRepository.findById(id);
	return optional.orElseThrow(() -> new ResourceNotFoundException("Não encontrado."));
    }
    
    public List<Video> buscarVideoPorTitulo(String titulo){
	return videoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public boolean verificarCategoria(VideoDTO videoDTO) {
	if (videoDTO.getCategoriaId() == null) {
	    return true;
	}
	return false;
    }
    
    public void verificarListaVazia(List<Video> videos) {
	if (videos.isEmpty()) {
	    throw new ResourceNotFoundException("Recurso não encontrado.");
	}
    }

    public Video inserirVideo(Video video) {
	return videoRepository.save(video);
    }

    public void deletarVideo(Video video) {
	videoRepository.delete(video);
    }

}
