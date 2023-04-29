package com.mfpe.company.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //generates a constructor requiring argument for every field in the annotated class
@NoArgsConstructor
public class SurveyorDto {

	private int surveyor_Id;
	private String first_Name;
	private String last_Name;
	private int estimate_Limit;

}
