package com.mfpe.surveyor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.surveyor.dto.SurveyDto;
import com.mfpe.surveyor.model.SurveyReport;
import com.mfpe.surveyor.repository.SurveyReportRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SurveyReportServiceImplTest {
	
	@InjectMocks
	private SurveyReportServiceImpl surveyReportService;
	
	@Mock
	private SurveyReportRepository surveyReportRepository;
	

	@Test
	void testAddSurvey() {
		SurveyDto surveyDto = new SurveyDto();
		surveyDto.setClaimId("CL1234");
		surveyDto.setPolicyNo("PL1234");
		surveyDto.setPartsCost(5000);
		surveyDto.setDepreciationCost(2000);
		surveyDto.setPolicyClass(1);
		surveyDto.setLabourCharges(1000);
		surveyDto.setTotalAmount(8001);
		
		SurveyReport surveyReport = new SurveyReport();
		surveyReport.setClaimId("CL1234");
		surveyReport.setPolicyNo("PL1234");
		surveyReport.setPartsCost(5000);
		surveyReport.setDepreciationCost(2000);
		surveyReport.setPolicyClass(1);
		surveyReport.setLabourCharges(1000);
		surveyReport.setTotalAmount(8000);
		
		when(surveyReportRepository.save(any(SurveyReport.class))).thenReturn(surveyReport);
		
		 SurveyDto result = surveyReportService.addSurvey(surveyDto);
		assertEquals(surveyDto, result);
	}

	@Test
	void testGetSurvey() {
		SurveyReport surveyReport = new SurveyReport();
		surveyReport.setClaimId("CL1234");
		surveyReport.setPolicyNo("PL1234");
		surveyReport.setPartsCost(5000);
		surveyReport.setDepreciationCost(2000);
		surveyReport.setPolicyClass(1);
		surveyReport.setLabourCharges(1000);
		surveyReport.setTotalAmount(8000);
		
		when(surveyReportRepository.findById(anyString())).thenReturn(Optional.of(surveyReport));
		
		SurveyDto result = surveyReportService.getSurvey("CL1234");
		assertEquals("CL1234", result.getClaimId());
		assertEquals("PL1234", result.getPolicyNo());
		assertEquals(5000, result.getPartsCost());
		assertEquals(2000, result.getDepreciationCost());
		assertEquals(1, result.getPolicyClass());
		assertEquals(1000, result.getLabourCharges());
		assertEquals(8000, result.getTotalAmount());
	}
	 @Test
	     void testUpdateSurvey() {
	        String claimId = "CL1234";
	        SurveyDto surveyDto = new SurveyDto();
	        surveyDto.setPolicyNo("PL1234");
	        surveyDto.setClaimId(claimId);
	        surveyDto.setPartsCost(1000);
	        surveyDto.setDepreciationCost(200);
	        surveyDto.setPolicyClass(2);
	        surveyDto.setLabourCharges(500);
	        surveyDto.setTotalAmount(1702);
	        
	        SurveyReport surveyReport = new SurveyReport();
	        surveyReport.setClaimId(claimId);
	        surveyReport.setPolicyNo("PL1234");
	        surveyReport.setPartsCost(1000);
	        surveyReport.setDepreciationCost(200);
	        surveyReport.setPolicyClass(2);
	        surveyReport.setLabourCharges(500);
	        Optional<SurveyReport> optSurvey = Optional.of(surveyReport);
	        Mockito.when(surveyReportRepository.findById(claimId)).thenReturn(optSurvey);
	        Mockito.when(surveyReportRepository.save(surveyReport)).thenReturn(surveyReport);
	         SurveyDto result = surveyReportService.updateSurvey(claimId, surveyDto);
	        assertEquals(result,surveyDto);
	        assertEquals(surveyReport.getClaimId(), claimId);
	        assertEquals(surveyReport.getPolicyNo(), surveyDto.getPolicyNo());
	        assertEquals(surveyReport.getPartsCost(), surveyDto.getPartsCost());
	        assertEquals(surveyReport.getDepreciationCost(), surveyDto.getDepreciationCost());
	        assertEquals(surveyReport.getPolicyClass(), surveyDto.getPolicyClass());
	        assertEquals(surveyReport.getLabourCharges(), surveyDto.getLabourCharges());

	        }        		
}