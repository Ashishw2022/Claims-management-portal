package com.cognizant.microservice.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendingStatusReportKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String reportId;
	private String stage;
}
