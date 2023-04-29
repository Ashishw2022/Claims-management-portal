package com.cognizant.microservice.services.impl;

import java.time.Month;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.IrdaMicroserviceApplication;
import com.cognizant.microservice.entity.PaymentOfClaim;
import com.cognizant.microservice.repository.PaymentOfClaimsRepository;
import com.cognizant.microservice.repository.PendingStatusReportsRepository;
import com.cognizant.microservice.services.PaymentOfClaimsService;


@Service
public class PaymentOfClaimsServiceImpl implements PaymentOfClaimsService {
	
	@Autowired
	PendingStatusReportsRepository pendingStatusReportsRepository;
	
	@Autowired
	PaymentOfClaimsRepository paymentOfClaimsRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IrdaMicroserviceApplication.class);
	
	String convertMonth(int month) {
		Month month1 = Month.of(month);
        String monthCode = month1.toString();
        return monthCode;
	}
	
	String generateId(int month, int year) {
		String monthCode = convertMonth(month);
        String month_Code = monthCode.substring(0, 3);
        int yearCode = year % 100;
        String id = "PS"+month_Code+yearCode;
        return id;
	}

	@Override
	public void pullPaymentStatus(int month, int year) {

		LOGGER.info("Start");
		
        List<Object[]> paymentStatusList = pendingStatusReportsRepository.paymentStatus(month, year);
        Long paymentOfClaim = 0L;

        if (!paymentStatusList.isEmpty()) {
            Object[] row = paymentStatusList.get(0);
            paymentOfClaim = (Long) row[1];
        }        
		PaymentOfClaim paymentOfClaims = new PaymentOfClaim();
		paymentOfClaims.setReportId(generateId(month,year));
		paymentOfClaims.setMonth(convertMonth(month));
		paymentOfClaims.setYear(year);
		paymentOfClaims.setPayment((paymentOfClaim).intValue());
		paymentOfClaimsRepository.save(paymentOfClaims);
		
		 LOGGER.info("End");
	}

}
