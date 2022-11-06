package br.com.challenge.videoflix.categories;

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
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	CategoriesRepository categoriesRepository;

	@GetMapping
	public ResponseEntity<List<CategoriesDto>> categoriesDetail(@RequestParam(required = false) String title) {
		List<CategoriesDto> list = (Strings.isBlank(title) ? categoriesRepository.findAll()
				: categoriesRepository.findByTitle(title)).stream().map(i -> new CategoriesDto(i))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriesDto> detailById(@PathVariable Long id) {
		Optional<Categories> categoriesFindById = categoriesRepository.findById(id);
		if (categoriesFindById.isPresent()) {
			return ResponseEntity.ok(new CategoriesDto(categoriesFindById.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> addNewCategories(@RequestBody @Valid CategoriesForm request,
			UriComponentsBuilder uriBuilder) {
		Categories entity = request.uptade(new Categories());
		Categories categories = categoriesRepository.save(entity);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(categories.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<String> updateCategories(@PathVariable Long id, @RequestBody @Valid CategoriesForm request) {
		Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
		if (categoriesOptional.isPresent()) {
			Categories categories = categoriesOptional.get();
			request.uptade(categories);
			categoriesRepository.save(categories);
			return ResponseEntity.ok("Category updated");
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		Optional<Categories> deleteFindById = categoriesRepository.findById(id);
		if (deleteFindById.isPresent()) {
			categoriesRepository.deleteById(id);
			return ResponseEntity.ok("Category Deleted");
		}
		return ResponseEntity.notFound().build();
	}

}
