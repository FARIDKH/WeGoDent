package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM patients p JOIN users u ON p.user.id = u.id WHERE u.id = :userId")
    Patient findByUserId(@Param("userId") Long userId);

}
