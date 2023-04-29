package com.cognizant.microservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.services.ClaimStatusService;


@RestController
@RequestMapping("/api/v1/irda")
public class ClaimStatusController {
	
	@Autowired
	ClaimStatusService claimStatusService;
	
    @GetMapping("/claimStatus/report/{month}/{year}")
    HashMap<String,Integer> claimStatus(@PathVariable String month,@PathVariable int year) {
   	  return claimStatusService.claimStatus(month, year);
    }

}
