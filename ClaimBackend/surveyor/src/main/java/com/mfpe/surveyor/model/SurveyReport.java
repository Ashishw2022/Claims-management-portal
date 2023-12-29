package com.mfpe.surveyor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter

public class SurveyReport {
	@Column(name = "claim_Id")
	@Id
	private String claimId;
	@Column(name = "policy_no")
	private String policyNo;
	@Column(name = "labour_charges")
	@Min(value = 0, message = "Labour charges cannot be negative")
	private int labourCharges;
	@Column(name = "parts_cost")
	@Min(value = 0, message = "Spare part cost cannot be negative")
	private int partsCost;
	@Column(name = "policy_class")
	@Min(value = 1, message = "Policy class should be greater than 0")
	private int policyClass;
	@Column(name = "depreciation_cost")
	private int depreciationCost;
	@Column(name = "total_amount")
	@Min(value = 0, message = "Total claim amount cannot be negative")
	private int totalAmount;

}
