package com.cognizant.microservice.services.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.IrdaMicroserviceApplication;
import com.cognizant.microservice.repository.ClaimStatusRepository;
import com.cognizant.microservice.services.ClaimStatusService;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService  {
	
	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IrdaMicroserviceApplication.class);

	@Override
	public HashMap<String, Integer> claimStatus(String month, int year) {
		
		LOGGER.info("Start");
		
		int newClaim = claimStatusRepository.newClaims(month, year);
		int pendingClaim = claimStatusRepository.pendingClaims(month, year);
		int finalizedClaim = claimStatusRepository.finalizedClaims(month, year);
		
		
		HashMap<String, Integer> myMap = new HashMap<>();
		myMap.put("New_claims", newClaim);
	    myMap.put("Pending_claims", pendingClaim);
	    myMap.put("Finalized_claims", finalizedClaim);
	    
	    LOGGER.info("End");
	    
	    
	    return myMap;
	    
	    

	}

}
