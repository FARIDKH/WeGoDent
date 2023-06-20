package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.Doctor;
import com.company.WeGoDent.enums.DoctorType;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "SELECT * FROM doctors d WHERE d.doctorType = :type AND ST_DWithin(d.location, ST_MakePoint(:longitude, :latitude), :distance)", nativeQuery = true)
    List<Doctor> findNearby(@Param("type") DoctorType type, @Param("longitude") double longitude, @Param("latitude") double latitude, @Param("distance") double distance);



}
