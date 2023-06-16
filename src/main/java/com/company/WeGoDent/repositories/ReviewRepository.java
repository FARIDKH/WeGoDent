package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>  {

    @Query("SELECT avg(ra.rating) From Review ra Where ra.appointment.doctor.id = :doctorId")
    Double getAverageRatingOfDoctor(@Param("doctorId") Long doctorId);
}
