package com.mfpe.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.company.model.Policy;
import com.mfpe.company.service.PolicyService;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Policy> getAllPolicy()
	{
		return policyService.getPolicy();
	}
	
	@PostMapping("/new")
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
        Policy newPolicy = policyService.addPolicy(policy);
        return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);	
    }
}
