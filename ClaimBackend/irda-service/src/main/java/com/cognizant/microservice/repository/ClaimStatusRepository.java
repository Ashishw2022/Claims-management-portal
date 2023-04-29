package com.cognizant.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.microservice.entity.PendingStatusReport;


@Repository
public interface ClaimStatusRepository extends JpaRepository<PendingStatusReport, String> {
	
	@Query(value="SELECT SUM(count) FROM pending_status_report WHERE stage = 'New_claims' AND month =:month AND year=:year", nativeQuery = true)
	int newClaims(String month,int year);
	
	@Query(value="SELECT SUM(count) FROM pending_status_report WHERE stage = 'Pending_claims' AND month =:month AND year=:year", nativeQuery = true)
	int pendingClaims(String month,int year);

	@Query(value="SELECT SUM(count) FROM pending_status_report WHERE stage = 'Finalized_claims' AND month =:month AND year=:year", nativeQuery = true)
	int finalizedClaims(String month,int year);
}
