package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.entity.ClaimDetails;

@Repository
public interface ClaimDetailsRepo extends JpaRepository<ClaimDetails, String>{		
}
