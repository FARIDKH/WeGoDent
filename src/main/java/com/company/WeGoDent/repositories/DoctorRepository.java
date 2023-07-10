package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.entity.Patient;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.enums.DoctorType;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT * FROM doctors d WHERE d.doctor_type = :type AND ST_DWithin(geography(d.office_location), geography(ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)), :distance * 1000)", nativeQuery = true)
    List<Doctor> findNearby(@Param("type") int type, @Param("longitude") double longitude, @Param("latitude") double latitude, @Param("distance") double distance);

    @Query("SELECT p FROM patients p JOIN Appointment a ON p.id = a.patient.id WHERE a.doctor.id = :doctorId")
    List<Patient> findPatientsByDoctorId(@Param("doctorId") Long doctorId);


    @Query("SELECT d FROM doctors d JOIN users u ON d.user.id = u.id WHERE u.id = :userId")
    Doctor findByUserId(@Param("userId") Long userId);




}

