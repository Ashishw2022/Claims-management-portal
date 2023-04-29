package com.cognizant.microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOfClaim {
	
	@Id
	String reportId;
	@Min(value=0, message="payment should be positive")
	int payment;
	String month;
	int year;
}

