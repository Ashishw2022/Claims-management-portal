package com.mfpe.company.model;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class Policy {
	
	@Id
	@GeneratedValue(generator = "policyIdGenerator")
	@GenericGenerator(name = "policyIdGenerator", strategy = "com.mfpe.company.model.PolicyIdGenerator")
	@Column(name = "Pk_policy_no")
	private String Policy_No;
	@Size(min = 5, message = "Insured first name must be at least 5 characters long")
	private String Insured_First_Name;
	@Size(min = 5, message = "Insured last name must be at least 5 characters long")
	private String Insured_Last_Name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@NotNull(message = "Date of insurance is required")
	@PastOrPresent(message = "Date of insurance must not be earlier than current date")
	@Column(name="date_Of_Insurance")
	private Date date_Of_Insurance;
	
	private String Email_Id;
	private String Vehicle_No;
	
	private boolean Policy_Status;
	
	
	
//	@OneToMany(mappedBy="policy")
//    private List<ClaimDetails> claimDetails;
	
	
	
}
