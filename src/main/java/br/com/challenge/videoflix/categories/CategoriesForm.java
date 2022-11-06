package br.com.challenge.videoflix.categories;

import javax.validation.constraints.NotBlank;

public class CategoriesForm {

	private Long id;

	@NotBlank(message = "Field title is required")
	private String title;

	@NotBlank(message = "Field color is required")
	private String color;

	public Categories uptade(Categories categories) {
		categories.setTitle(title);
		categories.setColor(color);
		return categories;
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
