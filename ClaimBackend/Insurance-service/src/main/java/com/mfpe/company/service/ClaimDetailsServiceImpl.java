package com.mfpe.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.company.exception.ClaimNotFoundException;
import com.mfpe.company.exception.MaximumClaimLimitReachedException;
import com.mfpe.company.exception.PolicyNotFoundException;
import com.mfpe.company.model.ClaimDetails;
import com.mfpe.company.model.Policy;
import com.mfpe.company.repository.ClaimDetailsRepository;
import com.mfpe.company.repository.PolicyRepository;

@Service
public class ClaimDetailsServiceImpl implements ClaimDetailsService {

	@Autowired
	private ClaimDetailsRepository claimDetailsRepository;

	@Autowired
	private PolicyRepository policyRepository;

//	@Autowired
//	private SurveyorRepository surveyorRepository;

	@Override
	public ClaimDetails addClaim(ClaimDetails claimDetails) {

		Optional<Policy> optPolicy = policyRepository.findById(claimDetails.getPolicy_No());
		if (!optPolicy.isPresent()) {
			throw new PolicyNotFoundException("Policy not found");
		}

		String policyNum = claimDetails.getPolicy_No().substring(claimDetails.getPolicy_No().length() - 4);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(java.util.Calendar.YEAR) % 10000;
		claimDetails.setClaim_Id("CL" + policyNum + String.format("%04d", year));

		Optional<ClaimDetails> optClaim = claimDetailsRepository.findById(claimDetails.getClaim_Id());
		if (optClaim.isPresent()) {
			throw new MaximumClaimLimitReachedException("Maximum claim limit exceeded for the year");
		}

		int loss = claimDetails.getEstimated_Loss();
		if (loss >= 5000 && loss < 10000) {
			claimDetails.setSurveyor_Fees(1000);
		} else if (loss >= 10000 && loss < 20000) {
			claimDetails.setSurveyor_Fees(2000);
		} else if (loss >= 20000 && loss < 70000) {
			claimDetails.setSurveyor_Fees(7000);
		}

		claimDetails.setClaim_Status("open");

		return claimDetailsRepository.save(claimDetails);
	}

	@Override
	public ClaimDetails updateClaim(String Claim_Id, ClaimDetails claimDetails) {

		Optional<ClaimDetails> optClaim = claimDetailsRepository.findById(Claim_Id);
		if (!optClaim.isPresent()) {
			throw new ClaimNotFoundException("Claim not found");
		}

		ClaimDetails claim = optClaim.get();

		if (claimDetails.getDate_Of_Accident() != null) {
			claim.setDate_Of_Accident(claimDetails.getDate_Of_Accident());
		}
		// if(claimDetails.isInsurance_Company_Approval() != (!true||!false)) {
		claim.setInsurance_Company_Approval(claimDetails.isInsurance_Company_Approval());
		// }
		if (claimDetails.getAmount_ApprovedBy_Surveyor() != 0) {
			claim.setAmount_ApprovedBy_Surveyor(claimDetails.getAmount_ApprovedBy_Surveyor());
		}
		if (claimDetails.getClaim_Status() != null) {
			claim.setClaim_Status(claimDetails.getClaim_Status());
		}
		// if(claimDetails.isWith_Draw_Claim() != (!true||!false)) {
		claim.setWith_Draw_Claim(claimDetails.isWith_Draw_Claim());
		// }
		if (claimDetails.getSurveyor_Id() != 0) {
			claim.setSurveyor_Id(claimDetails.getSurveyor_Id());
		}
		if (claimDetails.getPolicy_No() != null) {
			claim.setPolicy_No(claimDetails.getPolicy_No());
		}
		if (claimDetails.getEstimated_Loss() != 0) {
			claim.setEstimated_Loss(claimDetails.getEstimated_Loss());
		}
		if (claimDetails.getSurveyor_Fees() != 0) {
			claim.setSurveyor_Fees(claimDetails.getSurveyor_Fees());
		}

		claimDetailsRepository.save(claim);
		return claim;
	}

	@Override
	public int findPendingClaimsByMonthAndYear(int month, int year) {
		return claimDetailsRepository.findPendingClaimsByMonthAndYear(month, year);
	}

	@Override
	public int findAmountApprovedByInsuranceCompany(int month, int year) {
		return claimDetailsRepository.findAmountApprovedByInsuranceCompany(month, year);
	}

	@Override
	public List<ClaimDetails> getClaims() {

		return (List<ClaimDetails>) claimDetailsRepository.findAll();
	}

	@Override
	public String updateApproveClaimAmount(String Claim_Id, int claimant) {

		Optional<ClaimDetails> optClaim = claimDetailsRepository.findById(Claim_Id);
		if (!optClaim.isPresent()) {
			throw new ClaimNotFoundException("Claim not found");
		}

		ClaimDetails claim = optClaim.get();

		if (claimant >= 0) {
			claim.setAmount_ApprovedBy_Surveyor(claimant);
			claimDetailsRepository.save(claim);
			return "Amount Updated successfully";
		} else {
			return "Amount cant be updated";
		}

	}

	@Override
	public ClaimDetails findClaimDetailsById(String claimId) {
		
		return claimDetailsRepository.findById(claimId).get();
	}

}
