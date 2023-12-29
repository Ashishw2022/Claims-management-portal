package com.cognizant.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cognizant.microservice.services.PendingStatusReportsService;


@CrossOrigin("http://localhost:4200")
@RestController
//@RequestMapping("/api/v1/irda")
public class PendingStatusReportsController {
	
	@Autowired
	PendingStatusReportsService pendingStatusReportsService;
	
	
  
}
