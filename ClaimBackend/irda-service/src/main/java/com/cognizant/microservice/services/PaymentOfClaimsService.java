package com.cognizant.microservice.services;

public interface PaymentOfClaimsService {

	void pullPaymentStatus(int month, int year);
}
