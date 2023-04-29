package com.cognizant.microservice.services;

import java.util.List;

public interface PaymentsMonthWiseService {
	List<Object[]> paymentStatus(String month, int year);
}
