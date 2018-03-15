package com.thomsonreuters.cpl.cuasapi.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;

import com.thomsonreuters.cpl.cuasapi.exceptions.ExceptionResponse.ErrorCode;

@Order(value = 0)
@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ExceptionResponse> handleAnyException(Exception e) {
		//addAndRemoveStackTraceToMDCContext(e);
		/*ExceptionResponse expResponse = new ExceptionResponse(ErrorCode.UNKNOWN_ERROR, "System encountered exception.");
		return expResponse;*/
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ExceptionResponse(ErrorCode.UNKNOWN_ERROR, e.getMessage()));
	}

	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<ExceptionResponse> handleInvalidInputException(InvalidInputException e) {
		//addAndRemoveStackTraceToMDCContext(e);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ExceptionResponse(ErrorCode.INVALID_INPUT, e.getMessage()));
	}
	
	@ExceptionHandler(value = { HttpMediaTypeNotAcceptableException.class })
	public ResponseEntity<ExceptionResponse> handleMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		//addAndRemoveStackTraceToMDCContext(e);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ExceptionResponse(ErrorCode.APPLICATION_ERROR, e.getMessage()));
	}
	
	//This is to catch the exceptions thrown by Spring validation framework(MethodArgumentNotValidException). 
	/*@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
			throws IOException {
		//addAndRemoveStackTraceToMDCContext(e);
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<ErrorDetail> errors = new ArrayList<>();
		for (FieldError fieldError : fieldErrors) {
			errors.add(new ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionResponse(
				ErrorCode.INVALID_INPUT, "Validation failed , see the error details for errors", errors));
	}*/
	
	/*private void addAndRemoveStackTraceToMDCContext(Throwable t) {
		MDC.put(LogLayout.STACK_TRACE, ExceptionUtils.getFullStackTrace(t));
		LOG.error(t.getMessage());
		// remove the stack trace from the context after logging, otherwise it
		// will log stack trace to every message we are logging
		MDC.remove(LogLayout.STACK_TRACE);
	}*/
	
}	
