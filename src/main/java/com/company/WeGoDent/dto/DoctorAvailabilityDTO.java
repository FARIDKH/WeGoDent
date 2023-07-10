package com.company.WeGoDent.dto;


import com.company.WeGoDent.entity.Doctor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorAvailabilityDTO {

    private Long id;
//
//    private Doctor doctor;


    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
