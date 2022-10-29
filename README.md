<h1 align="center"> VideoFlix REST API </h1>

![Badge Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Badge Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Badge Mysql](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Badge JUnit5](	https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Badge spring security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Badge completed](https://img.shields.io/static/v1?label=Status&message=In+Progress&color=yellow&style=for-the-badge)

In this challenge, I am developing a REST API for a video sharing platform.

The platform should allow the user to assemble playlists with links to their favorite videos, separated by categories.

The main features implemented were:

API with routes implemented following the best practices of the REST model;

Implementation of a database for information persistence;

Automated tests;

Authentication/authorization service to restrict access to information.


## ✔️ Technologies

- ``Java ``
- ``Eclipse IDE``
- ``Maven``
- ``Spring Boot``
- ``MySQL``

## :bulb: Project Features

- `Video`

  - `Register`: Video registration through a POST to "/videos" with title, description, and url information in the request body.
  <br />

  ```java
  @PostMapping
	@Transactional
	public ResponseEntity<String> addNewVideo(@RequestBody @Valid VideoForm request, UriComponentsBuilder uriBuilder) {
		Videos entity = request.update(new Videos());
		Videos videos = videosRepository.save(entity);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(videos.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
  ```

   ```json
      {
        "id": 1,
        "title": "Interstellar",
        "description": "A team of explorers travel through a wormhole in space in an attempt to ensure humanitys survival.",
        "url": "shorturl.at/elRT8"
    }
    ```
    
     - `Video Listing`: listing of all videos, and it must accept GET requests for the URI "/videos".
     <br /> 

    ```java
    @GetMapping
	public ResponseEntity<List<VideosDto>> videoDetail(@RequestParam(required = false) String description) {
		List<VideosDto> list = (Strings.isBlank(description) ? videosRepository.findAll()
				: videosRepository.findByDescription(description)).stream().map(i -> new VideosDto(i))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
  ```
  
     ```json
        {
        "id": 1,
        "title": "Interstellar",
        "description": "A team of explorers travel through a wormhole in space in an attempt to ensure humanitys survival.",
        "url": "shorturl.at/elRT8"
    },
    {
        "id": 4,
        "title": "Star Wars: Episode IV - A New Hope",
        "description": "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
        "url": "shorturl.at/AMW57"
    }
    ```
    
     - `Searching by Id`: Search video by id through a type reques GET "/video/{ID}".
     <br />
     
     ```java
     @GetMapping("/{id}")
	public ResponseEntity<VideosDto> detailById(@PathVariable Long id) {
		Optional<Videos> videoFindById = videosRepository.findById(id);
		if (videoFindById.isPresent()) {
			return ResponseEntity.ok(new VideosDto(videoFindById.get()));
		}
		return ResponseEntity.badRequest().build();
	}
  ```

    - `Update`: Uptade a video through a type request PUT "/video/{id}}".
    <br />

    ```java
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
  ```
    
    - `Delete`: Delete a video through a type request DELETE "/video/{id}".
     <br /> 

    ```java
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Videos> deleteOptional = videosRepository.findById(id);
		if (deleteOptional.isPresent()) {
			videosRepository.deleteById(id);
			return ResponseEntity.ok("Video Deleted");
		}
		return ResponseEntity.notFound().build();
	}
```
 _______________________________________________________________________________________________________________________________________________________________________
