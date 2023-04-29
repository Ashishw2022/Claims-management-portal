package com.mfpe.company;

import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;


import com.mfpe.company.controller.ClaimDetailsController;
import com.mfpe.company.controller.PolicyController;
import com.mfpe.company.controller.SurveyorController;
//import com.mfpe.company.controller.CompanyController;
import com.mfpe.company.model.ClaimDetails;
import com.mfpe.company.model.Policy;
import com.mfpe.company.model.Surveyor;
import com.mfpe.company.repository.ClaimDetailsRepository;
import com.mfpe.company.repository.PolicyRepository;
import com.mfpe.company.repository.SurveyorRepository;
import com.mfpe.company.service.ClaimDetailsService;

import com.mfpe.company.service.SurveyorServiceImpl;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
class InsuranceCompanyApplicationTests {
	
	@Mock
    private ClaimDetailsService claimDetailsService;
	
    @Mock
    private ClaimDetailsRepository claimDetailsRepository;
    
    @Mock
    private SurveyorRepository surveyorRepository;

    @InjectMocks
    private SurveyorServiceImpl surveyorService;
	

//    @InjectMocks
//    private CompanyController companyController; 
    
    @InjectMocks
    private ClaimDetailsController claimDetailsController;
    
    @InjectMocks
    private PolicyController policyController;
    
    @InjectMocks
    private SurveyorController surveyorController;
    
    @Test
    public void testAddClaim() {
        Policy policy = new Policy();
        policy.setPolicy_No("SA12323");

        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setPolicy_No("SA12323");
        claimDetails.setEstimated_Loss(7500);

        PolicyRepository policyRepository = Mockito.mock(PolicyRepository.class);
        Mockito.when(policyRepository.findById("SA12323")).thenReturn(Optional.of(policy));

        ClaimDetailsRepository claimDetailsRepository = Mockito.mock(ClaimDetailsRepository.class);
        Mockito.when(claimDetailsRepository.save(Mockito.any())).thenReturn(claimDetails);

    }
    
    @Test
    public void testAddPolicy()
    {
    	Policy policy = new Policy("AH83323","piyuushi","ahirwal",new Date(),"piyush23@gmail.com","89833",true);
    	

    	 PolicyRepository policyRepository = Mockito.mock(PolicyRepository.class);
         Mockito.when(policyRepository.findById("AH83323")).thenReturn(Optional.of(policy));
    	
    }
    
    @Test
    public void testUpdateClaim() {
        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setClaim_Id("CL23232023");
        claimDetails.setEstimated_Loss(10000);
        claimDetails.setDate_Of_Accident(new Date());
        claimDetails.setClaim_Status("open");
        claimDetails.setSurveyor_Id(1);
        claimDetails.setSurveyor_Fees(2000);

        ClaimDetailsRepository claimDetailsRepository = Mockito.mock(ClaimDetailsRepository.class);
        Mockito.when(claimDetailsRepository.findById("CL23232023")).thenReturn(Optional.of(claimDetails));


    }

    
    @Test
    public void testGetAll() {
        Surveyor surveyor1 = new Surveyor(1, "Anuj", "Rhitika", 5000);
        Surveyor surveyor2 = new Surveyor(2, "Ashish", "Rhitika", 10000);

        List<Surveyor> surveyors = Arrays.asList(surveyor1, surveyor2);

        when(surveyorRepository.findAll()).thenReturn(surveyors);

        List<Surveyor> result = surveyorService.getAll();

        assertEquals(surveyors, result);
    }
    
    @Test
    public void testFindPendingClaimsByMonthAndYear() {
        ClaimDetailsRepository claimDetailsRepository = mock(ClaimDetailsRepository.class);

        int expected = 0;
        when(claimDetailsRepository.findPendingClaimsByMonthAndYear(1, 2022)).thenReturn(expected);
        int actual = claimDetailsService.findPendingClaimsByMonthAndYear(1, 2022);

        assertEquals(expected, actual);

    }
    
//    @Test
//    public void testFindAmountApprovedByInsuranceCompany() {
//        int expectedAmount = 9000;
//        int month = 05;
//        int year = 2023;
//        
//        Mockito.when(claimDetailsRepository.findAmountApprovedByInsuranceCompany(month, year))
//               .thenReturn(expectedAmount);
//        
//        int actualAmount = claimDetailsService.findAmountApprovedByInsuranceCompany(month, year);
//        
//        assertEquals(expectedAmount, actualAmount);
//        Mockito.verify(claimDetailsRepository, Mockito.times(1))
//               .findAmountApprovedByInsuranceCompany(month, year);
//    }
    

   
}
