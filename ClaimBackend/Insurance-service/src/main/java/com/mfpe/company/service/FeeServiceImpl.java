package com.mfpe.company.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.company.model.Fees;
import com.mfpe.company.repository.FeesRepository;

@Service
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeesRepository feesRepository;
	
	
	@Override
	public Fees ApproveFees(String claimId) {
		Fees fee = feesRepository.findById(claimId).get();
	
		fee.setApprove(true);
		
		feesRepository.save(fee);
		return fee;
		
	}
	
	@Override
	public Fees ApproveFee(String claimId,Fees fees) {
		
		Optional<Fees>feeDetail=feesRepository.findById(claimId);
		
		Fees fees1=feeDetail.get();
		fees1.setApprove(true);
		feesRepository.save(fees1);
		return fees1;
		
	}


	@Override
	public Fees FindFeeById(String claimId) {
		
		return feesRepository.findById(claimId).get();
	}

	
}
