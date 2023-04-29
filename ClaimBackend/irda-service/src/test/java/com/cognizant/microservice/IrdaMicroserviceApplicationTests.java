package com.cognizant.microservice;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.cognizant.microservice.controller.PaymentsMonthWiseController;
import com.cognizant.microservice.entity.PaymentOfClaim;
import com.cognizant.microservice.entity.PendingStatusReport;
import com.cognizant.microservice.repository.ClaimStatusRepository;
import com.cognizant.microservice.repository.PaymentOfClaimsRepository;
import com.cognizant.microservice.repository.PendingStatusReportsRepository;
import com.cognizant.microservice.services.ClaimStatusService;
import com.cognizant.microservice.services.PaymentOfClaimsService;
import com.cognizant.microservice.services.PaymentsMonthWiseService;
import com.cognizant.microservice.services.PendingStatusReportsService;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.isA;


@AutoConfigureMockMvc
@SpringBootTest
class IrdaMicroserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private PaymentsMonthWiseController paymentsMonthWiseController;
	
	@MockBean
	private PaymentsMonthWiseService paymentsMonthWiseService;
	
	@MockBean
	private PaymentOfClaimsRepository paymentOfClaimsRepository;
	
	@MockBean
	private ClaimStatusRepository claimStatusRepository;
	
	@MockBean
	private PendingStatusReportsRepository pendingStatusReportsRepository;
	
	@MockBean
	private PendingStatusReportsService pendingStatusReportsService;
	
	@MockBean
	private PaymentOfClaimsService paymentOfClaimsService;
	
	@MockBean
	private ClaimStatusService claimStatusService;
	
	
	public String convertMonth(int month) {
		Month month1 = Month.of(month);
        String monthCode = month1.toString();
        return monthCode;
	}
	
	public String generateId(int month, int year) {
		String monthCode = convertMonth(month);
        String month_Code = monthCode.substring(0, 3);
        int yearCode = year % 100;
        String id = "CS"+month_Code+yearCode;
        return id;
	}
	
	@Test
	public void testpaymentStatus() throws Exception {
		mockMvc.perform(get("/api/v1/irda/paymentStatus/report/April/2023")
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void testclaimStatus() throws Exception {
		mockMvc.perform(get("/api/v1/irda/claimStatus/report/April/2023")
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists());
	}
	
	@Test
	public void testpullClaimStatus() throws Exception {
		mockMvc.perform(post("/api/v1/irda/claimStatus/pull/04/2023")
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testpullPaymentStatus() throws Exception {
		mockMvc.perform(post("/api/v1/irda/paymentStatus/pull/04/2023")
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}
	
	@Test
    public void testPaymentStatusService() throws Exception {
        String month = "April";
        int year = 2023;
        
     List<Object[]> payments = new ArrayList<Object[]>();
     Object[] payment1 = new Object[] { "APRIL", 12000};
     payments.add(payment1);
     
     when(paymentsMonthWiseService.paymentStatus(month, year)).thenReturn(payments);
         mockMvc.perform(get("/api/v1/irda/paymentStatus/report/{month}/{year}",month,year))
	         .andExpect(status().isOk());
	  }
	
	@Test
    public void testClaimStatusService() throws Exception {
        String month = "April";
        int year = 2023;
        
        HashMap<String, Integer> myMap = new HashMap<String, Integer>();
		myMap.put("New_claims", 2);
	    myMap.put("Pending_claims", 0);
	    myMap.put("Finalized_claims", 2);
     
     when(claimStatusService.claimStatus(month, year)).thenReturn(myMap);
         mockMvc.perform(get("/api/v1/irda/claimStatus/report/{month}/{year}",month,year))
              .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
	         .andExpect(status().isOk());
	  }
		
	@Test
    public void pullClaimStatus() throws Exception {
        int month = 04;
        int year = 2023;
        
        int newClaim = pendingStatusReportsRepository.newClaims(month, year);
		int pendingClaim = pendingStatusReportsRepository.pendingClaims(month, year);
		int finalizedClaim = pendingStatusReportsRepository.finalizedClaims(month, year);
        
		PendingStatusReport newclaimObj = new PendingStatusReport();
		 newclaimObj.setReportId(generateId(month,year));
		 newclaimObj.setMonth(convertMonth(month));
		 newclaimObj.setYear(year);
		 newclaimObj.setStage("New_claims");
		 newclaimObj.setCount(newClaim);
		claimStatusRepository.save( newclaimObj);
		 
		PendingStatusReport pendingclaimObj = new PendingStatusReport();
		  pendingclaimObj.setReportId(generateId(month,year));
		  pendingclaimObj.setMonth(convertMonth(month));
		  pendingclaimObj.setYear(year);
		  pendingclaimObj.setStage("Pending_claims");
		  pendingclaimObj.setCount(pendingClaim);
		 claimStatusRepository.save(pendingclaimObj);
			
	    PendingStatusReport finalizedclaimObj = new PendingStatusReport();
		   finalizedclaimObj.setReportId(generateId(month,year));
		   finalizedclaimObj.setMonth(convertMonth(month));
		   finalizedclaimObj.setYear(year);
		   finalizedclaimObj.setStage("Finalized_claims");
		   finalizedclaimObj.setCount(finalizedClaim);
		  claimStatusRepository.save(finalizedclaimObj);
		
		mockMvc.perform(post("/api/v1/irda/claimStatus/pull/{month}/{year}",month,year).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(pendingStatusReportsService, times(1)).pullClaimStatus(month, year);

     }
	
	@Test
    public void testpullPaymentStatusService() throws Exception {
        int month = 04;
        int year = 2023;
        
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
		paymentOfClaims.setPayment(Long.valueOf(paymentOfClaim).intValue());
		paymentOfClaimsRepository.save(paymentOfClaims);
		
		mockMvc.perform(post("/api/v1/irda/paymentStatus/pull/{month}/{year}",month,year).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
		
		verify(paymentOfClaimsService, times(1)).pullPaymentStatus(month, year);

     }
	



}
