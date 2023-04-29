package com.mfpe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfpe.company.model.ClaimDetails;

public interface ClaimDetailsRepository extends JpaRepository<ClaimDetails,String>{
	
	@Query(value="SELECT COUNT(cd) FROM ClaimDetails cd WHERE cd.Insurance_Company_Approval = false AND MONTH(cd.Date_Of_Accident) = :month AND YEAR(cd.Date_Of_Accident) = :year")
    int findPendingClaimsByMonthAndYear(@Param("month") int month, @Param("year") int year);
	
	
	//hql READ IT
	@Query(value="SELECT SUM(c.Amount_ApprovedBy_Surveyor) FROM ClaimDetails c WHERE MONTH(c.Date_Of_Accident) = :month AND YEAR(c.Date_Of_Accident) = :year AND c.Insurance_Company_Approval = true")
	int findAmountApprovedByInsuranceCompany(@Param("month") int month, @Param("year") int year);
    

}
