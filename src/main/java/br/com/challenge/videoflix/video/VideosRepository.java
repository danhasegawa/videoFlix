package br.com.challenge.videoflix.video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

	List<Videos> findByDescription(String description);

}
