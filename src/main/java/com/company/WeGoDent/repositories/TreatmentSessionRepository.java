package com.company.WeGoDent.repositories;


import com.company.WeGoDent.entity.TreatmentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentSessionRepository extends JpaRepository<TreatmentSession, Long> {
}
