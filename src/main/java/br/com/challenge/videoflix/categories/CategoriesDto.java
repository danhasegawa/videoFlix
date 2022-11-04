package br.com.challenge.videoflix.categories;

public class CategoriesDto {

	private Long id;
	private String title;
	private String color;

	public CategoriesDto (Categories categories) {
		this.id = categories.getId();
		this.title = categories.getTitle();
		this.color = categories.getColor();
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
