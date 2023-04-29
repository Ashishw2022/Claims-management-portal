package com.cts.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class ClaimDetails {
@Length(max=10)
@Id
@Column(name="PK_ClaimId")
private String ClaimId;
@Positive(message = "Estimate loss must be a positive")
private int EstimatedLoss;
@PastOrPresent(message = "Date of accident should be less then the current date")
private Date DateOfAccident;
@Column(insertable = true)
private boolean ClaimStatus;
@Column(columnDefinition = "int default 0")
private int AmountApprovedBySurveyor;
@Column(columnDefinition = "boolean default false")
private boolean InsuranceCompanyApproval;
@Column(columnDefinition = "boolean default false")
private boolean WithdrawClaim;
private int SurveyorFees;

@JsonIgnore
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "FK_Surveyor")
private Surveyor surveyor;

@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "FK_PolicyNo")
private Policy policy;

public String getClaimId() {
	return ClaimId;
}

public void setClaimId(String claimId) {
	ClaimId = claimId;
}

public int getEstimatedLoss() {
	return EstimatedLoss;
}

public void setEstimatedLoss(int estimatedLoss) {
	EstimatedLoss = estimatedLoss;
}

public Date getDateOfAccident() {
	return DateOfAccident;
}

public void setDateOfAccident(Date dateOfAccident) {
	DateOfAccident = dateOfAccident;
}

public boolean isClaimStatus() {
	return ClaimStatus;
}

public void setClaimStatus(boolean claimStatus) {
	ClaimStatus = claimStatus;
}

public int getAmountApprovedBySurveyor() {
	return AmountApprovedBySurveyor;
}

public void setAmountApprovedBySurveyor(int amountApprovedBySurveyor) {
	AmountApprovedBySurveyor = amountApprovedBySurveyor;
}

public boolean isInsuranceCompanyApproval() {
	return InsuranceCompanyApproval;
}

public void setInsuranceCompanyApproval(boolean insuranceCompanyApproval) {
	InsuranceCompanyApproval = insuranceCompanyApproval;
}

public boolean isWithdrawClaim() {
	return WithdrawClaim;
}

public void setWithdrawClaim(boolean withdrawClaim) {
	WithdrawClaim = withdrawClaim;
}

public int getSurveyorFees() {
	return SurveyorFees;
}

public void setSurveyorFees(int surveyorFees) {
	SurveyorFees = surveyorFees;
}

public Surveyor getSurveyor() {
	return surveyor;
}

public void setSurveyor(Surveyor surveyor) {
	this.surveyor = surveyor;
}

public Policy getPolicy() {
	return policy;
}

public void setPolicy(Policy policy) {
	this.policy = policy;
}

}
