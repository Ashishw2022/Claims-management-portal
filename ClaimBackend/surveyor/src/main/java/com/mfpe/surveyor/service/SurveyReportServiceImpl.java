package com.mfpe.surveyor.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.exception.SurveyReportNotFoundException;
import com.mfpe.surveyor.model.SurveyReport;
import com.mfpe.surveyor.repository.SurveyReportRepository;

@Service
public class SurveyReportServiceImpl implements SurveyReportService {

	@Autowired
	private SurveyReportRepository surveyReportRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyReportServiceImpl.class);

	@Override
	public SurveyDto addSurvey(SurveyDto surveyDto) {
		LOGGER.info("Start");

		SurveyReport surveyReport = new SurveyReport();
		surveyReport.setClaimId(surveyDto.getClaimId());
		surveyReport.setPolicyNo(surveyDto.getPolicyNo());
		surveyReport.setPartsCost(surveyDto.getPartsCost());
		surveyReport.setDepreciationCost(surveyDto.getDepreciationCost());
		surveyReport.setPolicyClass(surveyDto.getPolicyClass());
		surveyReport.setLabourCharges(surveyDto.getLabourCharges());
		Optional<SurveyReport> optSurvey = surveyReportRepository.findById(surveyDto.getClaimId());
		if (optSurvey.isPresent()) {
			throw new SurveyReportNotFoundException("claim id exists");
		}

		int totalAmount = surveyReport.getLabourCharges() + surveyReport.getPartsCost() + surveyReport.getPolicyClass()
				+ surveyReport.getDepreciationCost();
		surveyReport.setTotalAmount(totalAmount);

		surveyReportRepository.save(surveyReport);
		LOGGER.info("End");
		return mapToDTO(surveyReport);

	}

	@Override
	public SurveyDto getSurvey(String claimId) {
		LOGGER.info("Start");

		SurveyReport surveyReport = surveyReportRepository.findById(claimId)
				.orElseThrow(() -> new SurveyReportNotFoundException("Survey not found"));
		LOGGER.info("End");

		return mapToDTO(surveyReport);
	}

	@Override
	public SurveyDto updateSurvey(String claimId, SurveyDto surveyReport) {
		LOGGER.info("Start");

		Optional<SurveyReport> optSurvey = surveyReportRepository.findById(claimId);
		if (!optSurvey.isPresent()) {
			throw new SurveyReportNotFoundException("Survey not found");
		}

		SurveyReport survey = optSurvey.get();

		if (surveyReport.getDepreciationCost() != 0) {
			survey.setDepreciationCost(surveyReport.getDepreciationCost());
		}
		if (surveyReport.getLabourCharges() != 0) {
			survey.setLabourCharges(surveyReport.getLabourCharges());
		}
		if (surveyReport.getPartsCost() != 0) {
			survey.setPartsCost(surveyReport.getPartsCost());
		}
		if (surveyReport.getPolicyClass() != 0) {
			survey.setPolicyClass(surveyReport.getPolicyClass());
		}
		if (surveyReport.getTotalAmount() != 0) {
			survey.setTotalAmount(surveyReport.getTotalAmount());
		}
		if (surveyReport.getPolicyNo() != null) {
			survey.setPolicyNo(surveyReport.getPolicyNo());
		}
		int totalAmount = surveyReport.getLabourCharges() + surveyReport.getPartsCost() + surveyReport.getPolicyClass()
				+ surveyReport.getDepreciationCost();
		survey.setTotalAmount(totalAmount);
		surveyReportRepository.save(survey);
		LOGGER.info("End");
		return mapToDTO(survey);
	}

	// convert entity to dto
	private SurveyDto mapToDTO(SurveyReport surveyReport) {
		SurveyDto surveyDto = new SurveyDto();
		surveyDto.setClaimId(surveyReport.getClaimId());
		surveyDto.setPolicyNo(surveyReport.getPolicyNo());
		surveyDto.setPartsCost(surveyReport.getPartsCost());
		surveyDto.setDepreciationCost(surveyReport.getDepreciationCost());
		surveyDto.setPolicyClass(surveyReport.getPolicyClass());
		surveyDto.setLabourCharges(surveyReport.getLabourCharges());
		surveyDto.setTotalAmount(surveyReport.getTotalAmount());
		return surveyDto;
	}
}
