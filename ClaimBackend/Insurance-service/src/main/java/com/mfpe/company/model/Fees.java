package com.mfpe.company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Fees {
	
	@Id
	@Column(name = "Pk_fee_id")
	private String Fee_Id;
	private int Estimate_Start_Limit;
	private int Estimate_End_Limit;
	private int fees;
    private boolean Approve;
}
