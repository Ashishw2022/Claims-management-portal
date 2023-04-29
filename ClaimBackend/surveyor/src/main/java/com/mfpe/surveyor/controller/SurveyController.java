package com.mfpe.surveyor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.exception.SurveyReportNotFoundException;
import com.mfpe.surveyor.service.SurveyReportService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(title = "Surveyor Module", description = "This is Surveyor Module"))
public class SurveyController {

	@Autowired
	private SurveyReportService surveyReportService;

	@PostMapping("/api/surveyors/new")
	public ResponseEntity<SurveyDto> addSurvey(@RequestBody SurveyDto surveyDto) throws SurveyReportNotFoundException {
		SurveyDto result = surveyReportService.addSurvey(surveyDto);
		return new ResponseEntity<SurveyDto>(result, HttpStatus.CREATED);
	}

	@GetMapping("/api/survey/{claimId}")
	public SurveyDto getSurvey(@PathVariable String claimId) throws SurveyReportNotFoundException {
		return surveyReportService.getSurvey(claimId);
	}

	@PutMapping("/api/survey/{claimId}")
	public ResponseEntity<SurveyDto> updateClaim(@PathVariable String claimId, @RequestBody SurveyDto surveyDto)
			throws SurveyReportNotFoundException {
		SurveyDto result = surveyReportService.updateSurvey(claimId, surveyDto);
		return new ResponseEntity<SurveyDto>(result, HttpStatus.OK);

	}

}
