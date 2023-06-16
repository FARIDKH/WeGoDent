package com.company.WeGoDent.entity;


import com.company.WeGoDent.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    private LocalDateTime appointmentStart;

    private LocalDateTime appointmentEnd;


    @OneToMany(mappedBy = "appointment")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // Assuming an Enum 'Status' defined with 'SCHEDULED', 'CANCELLED', 'COMPLETED', 'APPROVED', 'REJECTED'

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Review review;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
