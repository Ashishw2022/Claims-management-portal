package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Surveyor;
@Repository
public interface SurveyorRepo extends JpaRepository<Surveyor, Integer>{
}
