package com.mfpe.surveyor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfpe.surveyor.model.SurveyReport;

public interface SurveyReportRepository extends JpaRepository<SurveyReport, String>{
}
