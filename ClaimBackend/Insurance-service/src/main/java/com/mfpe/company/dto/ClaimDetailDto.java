package com.mfpe.company.dto;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //generates a constructor requiring argument for every field in the annotated class
@NoArgsConstructor //generates a constructor with no parameter
public class ClaimDetailDto {

	
	private String claim_Id;
	private String policy_No;
	private int estimated_Loss;
	private Date date_Of_Accident;
	private String claim_Status;
	private int surveyor_Id;	
	private int amount_ApprovedBy_Surveyor;
	private boolean insurance_Company_Approval;
	private boolean with_Draw_Claim;
	private int surveyor_Fees;
	
}
