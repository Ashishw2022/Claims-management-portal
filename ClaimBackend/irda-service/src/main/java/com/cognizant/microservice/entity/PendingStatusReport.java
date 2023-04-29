package com.cognizant.microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PendingStatusReportKey.class)
public class PendingStatusReport {
	
	@Id
	String reportId;
	@Id
	String stage;
    @Min(value=0, message="count should be positive")
	int count;
	String month;
	int year;
	
}