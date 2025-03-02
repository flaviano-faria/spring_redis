package com.redis.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class ValidationExceptionHandler extends ResponseEntityExceptionHandler{





	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		 List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
	        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
	        String error;
	        for (FieldError fieldError : fieldErrors) {
	            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
	            errors.add(error);
	        }
	        for (ObjectError objectError : globalErrors) {
	            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
	            errors.add(error);
	        }
	        ApiError apiError = 
	                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	        return new ResponseEntity(apiError, headers, status);
	}
	
	
	
}
