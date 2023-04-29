package com.cognizant.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cognizant.microservice.services.PendingStatusReportsService;



@RestController
@RequestMapping("/api/v1/irda")
public class PendingStatusReportsController {
	
	@Autowired
	PendingStatusReportsService pendingStatusReportsService;
	
    @PostMapping("/claimStatus/pull/{month}/{year}")
    void pullClaimStatus(@PathVariable int month,@PathVariable int year) {
    	pendingStatusReportsService.pullClaimStatus(month, year);
    }
}
