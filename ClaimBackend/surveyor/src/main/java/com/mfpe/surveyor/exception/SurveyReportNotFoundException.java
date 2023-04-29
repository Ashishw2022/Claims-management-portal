package com.mfpe.surveyor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Survey Report not found")

public class SurveyReportNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SurveyReportNotFoundException() {
		super();

	}

	public SurveyReportNotFoundException(String message) {
		super(message);

	}

}
