package com.mfpe.company.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //generates a constructor requiring argument for every field in the annotated class
@NoArgsConstructor
public class FeeDetailDto {

	
	private String fee_Id;
	private int estimate_Start_Limit;
	private int estimate_End_Limit;
	private int fees;
    private boolean approve;

}
