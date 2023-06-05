package com.company.WeGoDent.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime appointmentStart;

    private LocalDateTime appointmentEnd;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // Assuming an Enum 'Status' defined with 'SCHEDULED', 'CANCELLED', 'COMPLETED', 'APPROVED', 'REJECTED'

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Review review;

}
