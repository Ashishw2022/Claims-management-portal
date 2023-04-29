package com.mfpe.company.service;

import java.util.List;

import com.mfpe.company.model.ClaimDetails;

public interface ClaimDetailsService {
	
	public ClaimDetails addClaim(ClaimDetails claimDetails);

	public ClaimDetails updateClaim(String Claim_Id, ClaimDetails claimDetails);
	
	public String updateApproveClaimAmount(String Claim_Id, int claimant);
	
	public int findPendingClaimsByMonthAndYear(int month,int year);
	
	public int findAmountApprovedByInsuranceCompany(int month, int year);

	public List<ClaimDetails> getClaims();

	public ClaimDetails findClaimDetailsById(String claimId);
}
