package br.com.challenge.videoflix.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErrorMessage {

	private HttpStatus status;
	private List<String> errors;

	public ApiErrorMessage(HttpStatus status, List<String> errors) {
		this.status = status;
		this.errors = errors;
	}

	public ApiErrorMessage(HttpStatus status, String error) {
		this.status = status;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public List<String> getErrors() {
		return errors;
	}

}
