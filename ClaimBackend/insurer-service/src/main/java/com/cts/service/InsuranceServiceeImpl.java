package com.cts.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.ClaimResponse;
import com.cts.repository.ClaimDetailsRepo;
import com.cts.repository.PolicyRepo;
import com.cts.repository.SurveyorRepo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class InsuranceServiceeImpl implements InsuranceService {
	@Autowired
	ClaimDetailsRepo claimDetailsRepo;
	@Autowired
	SurveyorRepo surveyorRepo;
	@Autowired
	PolicyRepo policyRepo;

	@Override
	public List<ClaimDetails> getAllClaims() {
		return claimDetailsRepo.findAll();

	}



	@Override
	public String addNewClaim(ClaimResponse claimResponse) {
		ClaimDetails claimDetails = new ClaimDetails();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String policyno = claimResponse.getPolicyNo().substring(0, 4);
		String year = dateFormat.format(claimResponse.getDateOfAccident()).substring(0, 4);
		String ClaimId="CL" + policyno + year;
		claimResponse.setClaimId("CL" + policyno + year);
		String PolicyId=claimResponse.getPolicyNo();
		Optional<Policy> policy=policyRepo.findById(PolicyId);
		Policy policy1 =new Policy();
		policy1.setPolicyNo(policy.get().getPolicyNo());
		policy1.setInsuredFirstName(policy.get().getInsuredFirstName());
		policy1.setInsuredLastName(policy.get().getInsuredLastName());
		policy1.setDateOfInsurance(policy.get().getDateOfInsurance());
		policy1.setEmailId(policy.get().getEmailId());
		policy1.setVehicleNo(policy.get().getVehicleNo());
		policy1.setStatus(policy.get().isStatus());
		claimResponse.setPolicy(policy1);
		claimResponse.setClaimStatus(true);
		if (!policy.isPresent()) {
			throw new RuntimeException("Enter Policy Id");
		}
		else {
			Optional<ClaimDetails> claim=claimDetailsRepo.findById(ClaimId);
			if (claim.isPresent()) {
				throw new RuntimeException("MaximumClaimLimitReachedException");
			}
			else {
			BeanUtils.copyProperties(claimResponse, claimDetails);
			int loss = claimDetails.getEstimatedLoss();
			if(loss>=5000 && loss<10000) {
				claimDetails.setSurveyorFees(1000);
			}
			else if(loss>=10000 && loss<20000) {
				claimDetails.setSurveyorFees(2000);
			}
			else if(loss>=20000 && loss<70000) {
				claimDetails.setSurveyorFees(7000);
			}
			claimDetailsRepo.save(claimDetails);
			return claimDetails.getClaimId();
			}
		}
	}
		
		
		
		

	

	@Override
	public ClaimDetails updateClaim(ClaimResponse claimResponse, String id) {
		ClaimDetails claimDetails = claimDetailsRepo.findById(id).get();
		claimDetails.setWithdrawClaim(claimResponse.isWithdrawClaim());
		claimDetails.setClaimStatus(claimResponse.isClaimStatus());
//		claimDetails.setClaimStatus(false);
		claimDetailsRepo.save(claimDetails);
		return claimDetails;
	}

//	claimDetails.setClaimStatus(claimResponse.isClaimStatus());
//	claimDetails.setAmountApprovedBySurveyor(claimResponse.getAmountApprovedBySurveyor());
//	claimDetails.setInsuranceCompanyApproval(claimResponse.isInsuranceCompanyApproval());
//	claimDetails.setSurveyor(claimResponse.getSurveyor());
//		claimDetails.setClaimId(claimResponse.getClaimId());
//		claimDetails.setDateOfAccident(claimResponse.getDateOfAccident());
//		claimDetails.setEstimatedLoss(claimResponse.getEstimatedLoss());
//		claimDetails.setPolicyNo(claimResponse.getPolicyNo());
//		claimDetails.setSurveyor(claimResponse.getSurveyor());
//		claimDetails.setSurveyorFees(claimResponse.getSurveyorFees());
//		claimDetails.setSurveyorId(claimResponse.getSurveyorId());
//		claimDetails.setWithdrawClaim(claimResponse.isWithdrawClaim());


}
