package com.cognizant.microservice.services;

import java.util.HashMap;

public interface ClaimStatusService {
	HashMap<String,Integer> claimStatus(String month, int year);
}
