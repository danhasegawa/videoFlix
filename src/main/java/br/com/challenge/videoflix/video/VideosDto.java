package br.com.challenge.videoflix.video;

public class VideosDto {

	private Long id;
	private String title;
	private String description;
	private String url;

	public VideosDto(Videos video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

}
