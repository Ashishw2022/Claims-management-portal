package com.cts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.ClaimResponse;

public interface InsuranceService {
public List<ClaimDetails> getAllClaims();
public String addNewClaim( ClaimResponse claimResponse);
public ClaimDetails updateClaim( ClaimResponse claimResponse,String id);
}
