package com.cognizant.microservice.entity;

import java.util.Date;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDetail {

	@Id
	@Column(name="PK_ClaimId")
	private String claimId;
	private int estimatedLoss;
	private Date dateOfAccident;
	private int claimStatus;
	private int amountApprovedBySurveyor;
	private int insuranceCompanyApproval;
	private int withdrawClaim;
	private int surveyorFees;


}