package com.mfpe.surveyor.service;

import com.mfpe.surveyor.dto.SurveyDto;

public interface SurveyReportService {

	SurveyDto addSurvey(SurveyDto surveyReport);

	SurveyDto getSurvey(String claimId);

	SurveyDto updateSurvey(String claimId, SurveyDto surveyReport);
}
