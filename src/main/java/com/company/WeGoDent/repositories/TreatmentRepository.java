package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository  extends JpaRepository<Treatment, Long> {
}
