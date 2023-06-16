package com.company.WeGoDent.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "treatment_phases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TreatmentPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "treatment_id")
    private Treatment treatment;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
