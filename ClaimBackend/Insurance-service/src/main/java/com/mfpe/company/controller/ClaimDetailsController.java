package com.mfpe.company.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.company.model.ClaimDetails;
import com.mfpe.company.service.ClaimDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/claims")
public class ClaimDetailsController {

	@Autowired
	private ClaimDetailsService claimDetailsService;
	
	@GetMapping
	public List<ClaimDetails> getClaims()
	{
		return claimDetailsService.getClaims();
	}
	
	
	@PostMapping("/new")
    public ResponseEntity<ClaimDetails> addClaim(@RequestBody ClaimDetails claimDetails) {
        ClaimDetails newClaim = claimDetailsService.addClaim(claimDetails);
        return new ResponseEntity<>(newClaim, HttpStatus.CREATED);	
    }
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{Claim_Id}/update")
	public ClaimDetails updateClaim(@PathVariable String ClaimId, @RequestBody ClaimDetails claimDetails) {
		return claimDetailsService.updateClaim(ClaimId,claimDetails);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{Claim_Id}/{claimant}/update")
	public String updateClaimForClaimAmount(@PathVariable String Claim_Id,@PathVariable int claimant) {
		return claimDetailsService.updateApproveClaimAmount(Claim_Id,claimant);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/pending-claims/{month}/{year}")
	public int findPendingClaimsByMonthAndYear(@PathVariable int month,@PathVariable int year) {
		return claimDetailsService.findPendingClaimsByMonthAndYear(month, year);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/amount-approved/{month}/{year}")
	public int findAmountApprovedByInsuranceCompany(@PathVariable int month,@PathVariable int year) {
		return claimDetailsService.findAmountApprovedByInsuranceCompany(month, year);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{Claim_Id}")
	public ClaimDetails FindCLaimById(@PathVariable String Claim_Id)
	{
		return claimDetailsService.findClaimDetailsById(Claim_Id);
	}
	
	
}
