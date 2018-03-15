package com.thomsonreuters.cpl.cuasapi.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(Include.NON_NULL)
public class ExceptionResponse {

	private ErrorCode errorCode;

	private String errorMessage;

	private List<ErrorDetail> errorDetails;

	static public enum ErrorCode {
		UNKNOWN_ERROR, INVALID_INPUT, ENGINE_ERROR, APPLICATION_ERROR, AUTHENTICATION_ERROR, AUTHORIZATION_ERROR
	}

	public ExceptionResponse(ErrorCode errorCode, String message) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(message);
	}
	
	public ExceptionResponse(){
		
	}

	public ExceptionResponse(ErrorCode errorCode, String message, List<ErrorDetail> errorDetails) {
		this.setErrorCode(errorCode);
		this.setErrorMessage(message);
		this.setErrorDetails(errorDetails);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public List<ErrorDetail> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
	}

}
