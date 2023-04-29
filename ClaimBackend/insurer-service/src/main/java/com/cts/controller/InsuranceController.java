package com.cts.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.ClaimResponse;
import com.cts.service.InsuranceService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InsuranceController{
@Autowired
private InsuranceService insuranceService;
@PostMapping("/claims/addClaim")
public String addNewClaim(@RequestBody ClaimResponse claimResponse) {
	String ClaimId=insuranceService.addNewClaim(claimResponse);
    return ClaimId;
}
@PutMapping("/claims/update/{id}")
public ResponseEntity<ClaimDetails> updateClaim(@RequestBody ClaimResponse claimResponse,@PathVariable String id) {
	insuranceService.updateClaim(claimResponse,id);
	return new ResponseEntity<>(HttpStatus.CREATED);
}

}
