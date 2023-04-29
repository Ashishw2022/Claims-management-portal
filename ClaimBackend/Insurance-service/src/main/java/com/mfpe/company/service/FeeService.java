package com.mfpe.company.service;

import com.mfpe.company.model.Fees;

public interface FeeService {

 public Fees ApproveFee(String claimId,Fees fees);
 
 public Fees ApproveFees(String claimId);
 
 public Fees FindFeeById(String claimId);
 
}
