package com.mfpe.company.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.company.model.Fees;
import com.mfpe.company.service.FeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/surveyorfees")
public class FeeController {
	
	@Autowired
	private FeeService feeService;
	
//	@ResponseStatus(HttpStatus.OK)
//	@PutMapping("/{claimId}")
//    public Fees UpdateFeeApproval(@PathVariable String claimId)
//    {
//		return feeService.ApproveFee(claimId);
//    }
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{claimId}")
    public Fees UpdateFeeApproval(@PathVariable String claimId,@RequestBody Fees fee)
    {
      return feeService.ApproveFee(claimId, fee);
    }
	
	@GetMapping("/{claim_Id}")
	public Fees GetFeeDetailsById(@PathVariable String claim_Id ){
	return feeService.FindFeeById(claim_Id);	
	}
	

}

