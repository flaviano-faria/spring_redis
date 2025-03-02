package com.redis.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus apiStatus;
    private String apiMessage;
    private List<String> apiErrors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.apiStatus = status;
        this.apiMessage = message;
        this.apiErrors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.apiStatus = status;
        this.apiMessage = message;
        apiErrors = Arrays.asList(error);
    }

	public HttpStatus getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(HttpStatus status) {
		this.apiStatus = status;
	}

	public String getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(String message) {
		this.apiMessage = message;
	}

	public List<String> getApiErrors() {
		return apiErrors;
	}

	public void setApiErrors(List<String> errors) {
		this.apiErrors = errors;
	}
}
