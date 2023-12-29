package com.cognizant.microservice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.services.ClaimStatusService;
import com.cognizant.microservice.services.PaymentOfClaimsService;
import com.cognizant.microservice.services.PaymentsMonthWiseService;
import com.cognizant.microservice.services.PendingStatusReportsService;

@RestController
@CrossOrigin("http://localhost:4200")
public class ClaimStatusController {
	
	@Autowired
	ClaimStatusService claimStatusService;
	@Autowired
	PaymentOfClaimsService paymentOfClaimsService;
	@Autowired
	PaymentsMonthWiseService paymentsMonthWiseService;
	@Autowired
	PendingStatusReportsService pendingStatusReportsService;
	
    @GetMapping("/api/v1/irda/claimStatus/report/{month}/{year}")
    @PreAuthorize("hasAuthority('Role_irda')")
    HashMap<String,Integer> claimStatus(@PathVariable String month,@PathVariable int year) {
   	  return claimStatusService.claimStatus(month, year);
    }
    @PostMapping("/api/v1/irda/paymentStatus/pull/{month}/{year}")
    @PreAuthorize("hasAuthority('Role_irda')")
    void pullPaymentStatus(@PathVariable int month,@PathVariable int year) {
    	paymentOfClaimsService.pullPaymentStatus(month, year);
    }
    @GetMapping("/api/v1/irda/paymentStatus/report/{month}/{year}")
    @PreAuthorize("hasAuthority('Role_irda')")
    List<Object[]> paymentStatus(@PathVariable String month,@PathVariable  int year) {
   	 return paymentsMonthWiseService.paymentStatus(month, year);
    }
    @PostMapping("/api/v1/irda/claimStatus/pull/{month}/{year}")
    @PreAuthorize("hasAuthority('Role_irda')")
    void pullClaimStatus(@PathVariable int month,@PathVariable int year) {
    	pendingStatusReportsService.pullClaimStatus(month, year);
    }

}
