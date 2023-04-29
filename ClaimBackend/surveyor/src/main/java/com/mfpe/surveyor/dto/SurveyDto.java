package com.mfpe.surveyor.dto;

import lombok.Data;

@Data
public class SurveyDto {

	private String claimId;
	private String policyNo;
	private int labourCharges;
	private int partsCost;
	private int policyClass;
	private int depreciationCost;
	private int totalAmount;

}
