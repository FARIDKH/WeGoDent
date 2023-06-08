package com.company.WeGoDent.repositories;

import com.company.WeGoDent.models.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

}
