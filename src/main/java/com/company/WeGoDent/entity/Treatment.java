package com.company.WeGoDent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Long cost;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<TreatmentPhase> treatmentPhaseList = new ArrayList<>();

    public List<TreatmentPhase> getTreatmentPhaseList() {
        return treatmentPhaseList;
    }

    public void setTreatmentPhaseList(List<TreatmentPhase> treatmentPhaseList) {
        this.treatmentPhaseList = treatmentPhaseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
