package br.com.challenge.videoflix.video;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/videos")
public class VideosController {

	@Autowired
	VideosRepository videosRepository;

	@GetMapping
	public ResponseEntity<List<VideosDto>> videoDetail(@RequestParam(required = false) String title) {
		List<VideosDto> list = (Strings.isBlank(title) ? videosRepository.findAll()
				: videosRepository.findByTitle(title)).stream().map(i -> new VideosDto(i)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideosDto> detailById(@PathVariable Long id) {
		Optional<Videos> videoFindById = videosRepository.findById(id);
		if (videoFindById.isPresent()) {
			return ResponseEntity.ok(new VideosDto(videoFindById.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> addNewVideo(@RequestBody @Valid VideoForm request, UriComponentsBuilder uriBuilder) {
		Videos entity = request.update(new Videos());
		Videos videos = videosRepository.save(entity);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(videos.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid VideoForm request) {
		Optional<Videos> updateOptional = videosRepository.findById(id);
		if (updateOptional.isPresent()) {
			Videos videos = updateOptional.get();
			request.update(videos);
			videosRepository.save(videos);
			return ResponseEntity.ok("Videos Updated");
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Videos> deleteOptional = videosRepository.findById(id);
		if (deleteOptional.isPresent()) {
			videosRepository.deleteById(id);
			return ResponseEntity.ok("Video Deleted");
		}
		return ResponseEntity.notFound().build();
	}

}