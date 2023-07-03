package com.company.WeGoDent.entity;


import com.company.WeGoDent.enums.DoctorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity(name = "doctors")
@Table
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String experience;

    private String language;

    private Double hourlyRate;


    @Enumerated(EnumType.ORDINAL)
    private DoctorType doctorType;

    @Column(columnDefinition = "geography(Point,4326)")
    @JsonIgnore
    private Point officeLocation;

    private String officeLocationName;

    private String biography;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Inventory> inventories;



}
