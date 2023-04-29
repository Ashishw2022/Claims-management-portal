package com.mfpe.company.service;

import java.util.List;

import com.mfpe.company.model.Policy;

public interface PolicyService {
	
	public Policy addPolicy(Policy policy);
    
	public List<Policy> getPolicy();
}
