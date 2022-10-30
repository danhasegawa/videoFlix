package br.com.challenge.videoflix.video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

	List<Videos> findByTitle(String title);

	@Query("select count(c) from Videos c where lower(c.title) = lower(:title) and lower(c.description) = lower(:description) and lower(c.url) = lower(:url)")
	Long videosByDescription(String description);

}
