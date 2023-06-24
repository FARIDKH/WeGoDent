package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.PatientPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientPlanRepository extends JpaRepository<PatientPlan, Long> {
}
