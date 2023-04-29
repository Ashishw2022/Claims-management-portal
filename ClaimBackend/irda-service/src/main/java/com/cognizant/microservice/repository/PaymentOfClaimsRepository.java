package com.cognizant.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.microservice.entity.PaymentOfClaim;


@Repository
public interface PaymentOfClaimsRepository extends JpaRepository<PaymentOfClaim,String>{
	
	@Query(value="SELECT p.month, p.payment AS PaymentOfClaims FROM payment_of_claim p WHERE "
			+ "p.month =:month AND p.year =:year GROUP BY p.month, p.year "
			+ "ORDER BY p.month", nativeQuery = true)
	List<Object[]> paymentStatus(String month, int year);

}
