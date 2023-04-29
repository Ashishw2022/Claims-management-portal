package com.cognizant.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.microservice.entity.ClaimDetail;


@Repository
public interface PendingStatusReportsRepository extends JpaRepository<ClaimDetail,String> {
	
	@Query(value="SELECT COUNT(claimId) FROM ClaimDetail WHERE claimStatus = 1 AND month(dateOfAccident) =:month AND year(dateOfAccident) =:year")
	int newClaims(int month,int year);
	
	@Query(value="SELECT COUNT(claimId) FROM ClaimDetail WHERE insuranceCompanyApproval = 0 AND month(dateOfAccident) =:month AND year(dateOfAccident) =:year")
	int pendingClaims(int month,int year);
	
	@Query(value="SELECT COUNT(claimId) FROM ClaimDetail WHERE insuranceCompanyApproval = 1 AND month(dateOfAccident) =:month AND year(dateOfAccident) =:year")
	int finalizedClaims(int month,int year);
	
	@Query(value="SELECT MONTHNAME(c.dateOfAccident) AS Month, SUM(c.amountApprovedBySurveyor) AS PaymentOfClaims FROM ClaimDetail c WHERE "
			+ "MONTH(c.dateOfAccident) =:month AND YEAR(c.dateOfAccident) =:year GROUP BY MONTH(c.dateOfAccident), YEAR(c.dateOfAccident) "
			+ "ORDER BY MONTH(c.dateOfAccident)")
	List<Object[]> paymentStatus(int month, int year);

}
