package com.mfpe.surveyor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.exception.SurveyReportNotFoundException;
import com.mfpe.surveyor.model.SurveyReport;
import com.mfpe.surveyor.service.SurveyReportService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SurveyControllerTest {

	@Mock
	private SurveyReportService surveyReportService;

	@InjectMocks
	private SurveyController surveyController;

	@Test
	void testAddSurvey() throws SurveyReportNotFoundException {
		// Given
		SurveyDto surveyReportDto = new SurveyDto();
		surveyReportDto.setClaimId("CL1235050");
		surveyReportDto.setPolicyNo("PL1234");
		surveyReportDto.setPartsCost(5000);
		surveyReportDto.setDepreciationCost(2000);
		surveyReportDto.setPolicyClass(1);
		surveyReportDto.setLabourCharges(1000);
		surveyReportDto.setTotalAmount(8000);

		SurveyReport surveyReport = new SurveyReport();
		surveyReport.setClaimId("CL1235050");
		surveyReport.setPolicyNo("PL1234");
		surveyReport.setPartsCost(5000);
		surveyReport.setDepreciationCost(2000);
		surveyReport.setPolicyClass(1);
		surveyReport.setLabourCharges(1000);
		surveyReport.setTotalAmount(8000);

		// When
		when(surveyReportService.addSurvey(any(SurveyDto.class))).thenReturn(surveyReportDto);
		ResponseEntity<SurveyDto> response = surveyController.addSurvey(surveyReportDto);

		// Then
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(surveyReportDto, response.getBody());
	}

	@Test
	void testGetSurvey() throws SurveyReportNotFoundException {
		// Given
		SurveyDto surveyReportDto = new SurveyDto();
		surveyReportDto.setClaimId("CL1235050");
		surveyReportDto.setPolicyNo("PL1010231");
		surveyReportDto.setPartsCost(100);
		surveyReportDto.setDepreciationCost(10);
		surveyReportDto.setPolicyClass(50);
		surveyReportDto.setLabourCharges(50);
		surveyReportDto.setTotalAmount(160);

		// When
		when(surveyReportService.getSurvey("CL1235050")).thenReturn(surveyReportDto);
		SurveyDto response = surveyController.getSurvey("CL1235050");

		// Then
		assertEquals(surveyReportDto, response);
	}

	@Test
	void testUpdateClaim() throws SurveyReportNotFoundException {
		// Given
		SurveyDto surveyReportDto = new SurveyDto();
		surveyReportDto.setClaimId("CL1235050");
		surveyReportDto.setPolicyNo("PL1010231");
		surveyReportDto.setPartsCost(100);
		surveyReportDto.setDepreciationCost(10);
		surveyReportDto.setPolicyClass(50);
		surveyReportDto.setLabourCharges(50);
		surveyReportDto.setTotalAmount(160);

		// When
		when(surveyReportService.updateSurvey("CL1235050", surveyReportDto)).thenReturn(surveyReportDto);
		ResponseEntity<SurveyDto> response = surveyController.updateClaim("CL1235050", surveyReportDto);

		// Then
		assertEquals(surveyReportDto, response.getBody());
	}
}