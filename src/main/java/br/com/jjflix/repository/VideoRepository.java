package br.com.jjflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jjflix.domain.Video;

@Repository
public interface VideoRepository  extends JpaRepository<Video, Long>{

	
}
