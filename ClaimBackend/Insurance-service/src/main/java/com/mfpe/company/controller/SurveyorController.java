package com.mfpe.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mfpe.company.model.Surveyor;
import com.mfpe.company.service.SurveyorService;

@RestController
@RequestMapping("/api/surveyors")
public class SurveyorController {

	@Autowired
	private SurveyorService surveyorService;
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Surveyor> getAll() {
		return surveyorService.getAll();
	}

}
