package com.company.WeGoDent.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User patientId;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
