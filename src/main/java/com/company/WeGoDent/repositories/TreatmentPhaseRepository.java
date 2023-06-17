package com.company.WeGoDent.repositories;


import com.company.WeGoDent.entity.TreatmentPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentPhaseRepository extends JpaRepository<TreatmentPhase, Long> {
}
