package com.company.WeGoDent.repositories;

import com.company.WeGoDent.models.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findAllByDoctorId(Long doctorId);

    @Query("SELECT da FROM DoctorAvailability da WHERE da.doctor.id = :doctorId AND " +
            "(da.startDateTime <= :endDateTime AND da.endDateTime >= :startDateTime)")
    List<DoctorAvailability> findByDoctorIdAndDateTimeRange(@Param("doctorId") Long doctorId,
                                                            @Param("startDateTime") LocalDateTime startDateTime,
                                                            @Param("endDateTime") LocalDateTime endDateTime);

}
