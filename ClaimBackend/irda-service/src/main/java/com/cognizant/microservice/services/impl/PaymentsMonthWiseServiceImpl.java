package com.cognizant.microservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.repository.PaymentOfClaimsRepository;
import com.cognizant.microservice.services.PaymentsMonthWiseService;

@Service
public class PaymentsMonthWiseServiceImpl implements PaymentsMonthWiseService {

	@Autowired
	PaymentOfClaimsRepository paymentOfClaimsRepository;
	
	@Override
	public List<Object[]> paymentStatus(String month, int year) {
		return (paymentOfClaimsRepository.paymentStatus(month, year));
	}

}
