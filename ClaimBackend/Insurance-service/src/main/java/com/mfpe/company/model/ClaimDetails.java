package com.mfpe.company.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClaimDetails {
	
	@Id
//	@GeneratedValue(generator = "claimIdGenerator")
//	@GenericGenerator(name = "claimIdGenerator", strategy = "com.mfpe.company.model.ClaimIdGenerator")
	@Size(min = 10, max = 10, message = "Claim Id must be 10 characters long")
	@Column(name = "Pk_claim_id")
	private String Claim_Id;
	
	@Column(name = "Fk_policy_no")
	private String Policy_No;
	
	@Positive(message = "Estimated loss must be a positive value")
	private int Estimated_Loss;
	
	
	@PastOrPresent(message = "Date of accident cannot be in the future")
	private Date Date_Of_Accident;
	
	@Pattern(regexp = "^(open|closed)$", message = "Claim status can only be open or closed")
	private String Claim_Status;
	
	@Column(name = "Fk_surveyor_id")
	private int Surveyor_Id;
	
	@PositiveOrZero(message = "Amount approved by surveyor must be a positive value")
	private int Amount_ApprovedBy_Surveyor;
	
	private boolean Insurance_Company_Approval;
	
	private boolean With_Draw_Claim;
	
	@Min(value = 0, message = "Surveyor fees cannot be negative")
	private int Surveyor_Fees;	
	
	public void setDate_Of_Accident(Date date_Of_Accident) {
		Date_Of_Accident = date_Of_Accident;
	}

}
