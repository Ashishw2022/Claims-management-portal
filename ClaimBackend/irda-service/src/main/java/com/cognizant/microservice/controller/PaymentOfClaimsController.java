package com.cognizant.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.services.PaymentOfClaimsService;


@RestController
@RequestMapping("/api/v1/irda")
public class PaymentOfClaimsController {
	
	@Autowired
	PaymentOfClaimsService paymentOfClaimsService;
	
    @PostMapping("/paymentStatus/pull/{month}/{year}")
    void pullPaymentStatus(@PathVariable int month,@PathVariable int year) {
    	paymentOfClaimsService.pullPaymentStatus(month, year);
    }

}
