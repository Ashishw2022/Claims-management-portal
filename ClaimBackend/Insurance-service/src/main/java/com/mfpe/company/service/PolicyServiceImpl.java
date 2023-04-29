package com.mfpe.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.company.model.Policy;
import com.mfpe.company.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService{
	
	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public Policy addPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public List<Policy> getPolicy() {
		
		return (List<Policy>) policyRepository.findAll();
	}

}
