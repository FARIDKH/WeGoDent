package com.company.WeGoDent.entity;


import com.company.WeGoDent.enums.DoctorType;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User doctorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String experience;

    private String language;

    private Double hourlyRate;

    private DoctorType doctorType;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point officeLocation;

    public Point getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(Point officeLocation) {
        this.officeLocation = officeLocation;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }



    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    private String biography;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public User getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(User doctorId) {
        this.doctorId = doctorId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
