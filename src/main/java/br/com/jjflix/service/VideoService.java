package br.com.jjflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjflix.domain.Video;
import br.com.jjflix.repository.VideoRepository;
import br.com.jjflix.service.exception.NotFoundException;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> buscarVideos() {
	return videoRepository.findAll();
    }

    public Video buscarVideoPorId(Long id) {
	Optional<Video> optional = videoRepository.findById(id);
	return optional.orElseThrow(() -> new NotFoundException("Não encontrado."));
    }

    public Video inserirVideo(Video video) {
	return videoRepository.save(video);
    }
    
    public void deletarVideo(Video video) {
	 videoRepository.delete(video);
    }
}
