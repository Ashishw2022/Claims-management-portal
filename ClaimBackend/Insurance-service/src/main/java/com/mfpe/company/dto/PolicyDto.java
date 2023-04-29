package com.mfpe.company.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //generates a constructor requiring argument for every field in the annotated class
@NoArgsConstructor
public class PolicyDto {

	
	
	private String policy_No;
	private String insured_First_Name;
	private String insured_Last_Name;
	private Date date_Of_Insurance;
	private String email_Id;
	private String vehicle_No;
	private boolean policy_Status;
	
}
