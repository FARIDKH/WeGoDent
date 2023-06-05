package com.company.WeGoDent.models;


import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    private Integer rating;

    private String comment;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
