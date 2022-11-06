package br.com.challenge.videoflix.video;

import javax.validation.constraints.NotBlank;

public class VideoForm {

	private Long id;

	@NotBlank(message = "Field title is required")
	private String title;

	@NotBlank(message = "Field description is required")
	private String description;

	@NotBlank(message = "Field url is required")
	private String url;

	public Videos update(Videos videos) {

		videos.setTitle(title);
		videos.setDescription(description);
		videos.setUrl(url);
		return videos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
