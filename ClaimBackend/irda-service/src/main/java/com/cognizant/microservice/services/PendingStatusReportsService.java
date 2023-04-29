package com.cognizant.microservice.services;


public interface PendingStatusReportsService {
	
	void pullClaimStatus(int month, int year);
}
