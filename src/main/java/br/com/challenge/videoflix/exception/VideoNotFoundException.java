package br.com.challenge.videoflix.exception;

public class VideoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public VideoNotFoundException() {
		super();
	}

	@Override
	public String getMessage() {
		return "Video not found";
	}
}
