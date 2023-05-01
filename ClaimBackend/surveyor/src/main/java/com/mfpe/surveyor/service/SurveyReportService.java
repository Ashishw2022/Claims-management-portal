package com.mfpe.surveyor.service;

import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.model.UserInfo;

public interface SurveyReportService {

	SurveyDto addSurvey(SurveyDto surveyReport);

	SurveyDto getSurvey(String claimId);

	SurveyDto updateSurvey(String claimId, SurveyDto surveyReport);

	String addUser(UserInfo userInfo);
}
