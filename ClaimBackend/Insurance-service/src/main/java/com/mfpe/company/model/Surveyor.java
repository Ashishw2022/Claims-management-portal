package com.mfpe.company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Surveyor {
	
	@Id
	@PositiveOrZero(message = "Surveyor Id must be a positive value")
	@Column(name = "Pk_surveyor_id")
	private int Surveyor_Id;
	private String First_Name;
	private String Last_Name;
	private int Estimate_Limit;

}
