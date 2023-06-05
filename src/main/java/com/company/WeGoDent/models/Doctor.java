package com.company.WeGoDent.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
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

    private String biography;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
