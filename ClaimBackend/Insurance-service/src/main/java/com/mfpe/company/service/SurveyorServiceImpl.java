package com.mfpe.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.company.model.Surveyor;
import com.mfpe.company.repository.SurveyorRepository;

@Service
public class SurveyorServiceImpl implements SurveyorService{
	
	@Autowired
	private SurveyorRepository surveyorRepository;

	@Override
	public List<Surveyor> getAll() {
		return surveyorRepository.findAll();
	}

}
