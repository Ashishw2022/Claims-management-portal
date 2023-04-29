package com.cognizant.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.services.PaymentsMonthWiseService;


@RestController
@RequestMapping("/api/v1/irda")
public class PaymentsMonthWiseController {
	
	@Autowired
	PaymentsMonthWiseService paymentsMonthWiseService;
	
    @GetMapping("/paymentStatus/report/{month}/{year}")
    List<Object[]> paymentStatus(@PathVariable String month,@PathVariable  int year) {
   	 return paymentsMonthWiseService.paymentStatus(month, year);
    }
}
