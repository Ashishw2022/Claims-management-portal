package com.cognizant.microservice.services.impl;

import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.IrdaMicroserviceApplication;
import com.cognizant.microservice.entity.PendingStatusReport;
import com.cognizant.microservice.repository.ClaimStatusRepository;
import com.cognizant.microservice.repository.PendingStatusReportsRepository;
import com.cognizant.microservice.services.PendingStatusReportsService;


@Service
public class PendingStatusReportsServiceImpl implements PendingStatusReportsService{

	
	@Autowired
	PendingStatusReportsRepository pendingStatusReportsRepository;
	
	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IrdaMicroserviceApplication.class);
	
	public String convertMonth(int month) {
		Month month1 = Month.of(month);
        String monthCode = month1.toString();
        return monthCode;
	}
	
	public String generateId(int month, int year) {
		String monthCode = convertMonth(month);
        String month_Code = monthCode.substring(0, 3);
        int yearCode = year % 100;
        String id = "CS"+month_Code+yearCode;
        return id;
	}
    
	public void pullClaimStatus(int month, int year) {
		
		LOGGER.info("Start");

		int newClaim = pendingStatusReportsRepository.newClaims(month, year);
		int pendingClaim = pendingStatusReportsRepository.pendingClaims(month, year);
		int finalizedClaim = pendingStatusReportsRepository.finalizedClaims(month, year);
		
		PendingStatusReport newclaimObj = new PendingStatusReport();
		 newclaimObj.setReportId(generateId(month,year));
		 newclaimObj.setMonth(convertMonth(month));
		 newclaimObj.setYear(year);
		 newclaimObj.setStage("New_claims");
		 newclaimObj.setCount(newClaim);
		 claimStatusRepository.save( newclaimObj);
		
		PendingStatusReport pendingclaimObj = new PendingStatusReport();
		pendingclaimObj.setReportId(generateId(month,year));
		pendingclaimObj.setMonth(convertMonth(month));
		pendingclaimObj.setYear(year);
		pendingclaimObj.setStage("Pending_claims");
		pendingclaimObj.setCount(pendingClaim);
		claimStatusRepository.save(pendingclaimObj);
		
		PendingStatusReport finalizedclaimObj = new PendingStatusReport();
		finalizedclaimObj.setReportId(generateId(month,year));
		finalizedclaimObj.setMonth(convertMonth(month));
		finalizedclaimObj.setYear(year);
		finalizedclaimObj.setStage("Finalized_claims");
		finalizedclaimObj.setCount(finalizedClaim);
		claimStatusRepository.save(finalizedclaimObj);
		
		LOGGER.info("End");
		
	}

}
