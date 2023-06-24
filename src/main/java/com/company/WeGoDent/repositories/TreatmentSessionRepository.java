package com.company.WeGoDent.repositories;


import com.company.WeGoDent.entity.TreatmentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentSessionRepository extends JpaRepository<TreatmentSession, Long> {


    @Query("SELECT ts FROM treatment_sessions ts JOIN ts.appointment a WHERE a.patient.id = :patientId")
    List<TreatmentSession> findByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT ts FROM treatment_sessions ts JOIN ts.treatmentPhase tp JOIN tp.treatment t WHERE t.name = :treatmentName")
    List<TreatmentSession> findByTreatmentName(@Param("treatmentName") String treatmentName);



}
