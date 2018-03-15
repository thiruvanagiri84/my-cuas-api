package com.thomsonreuters.cpl.cuasapi.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Not needed for this cuasapi project.
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorDetail {

	public ErrorDetail() {

	}

	public ErrorDetail(String key, String error) {
		this.key = key;
		this.error = error;
	}

	private String key;

	private String error;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
