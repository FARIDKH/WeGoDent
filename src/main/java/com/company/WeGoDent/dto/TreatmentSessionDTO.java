package com.company.WeGoDent.dto;

public class TreatmentSessionDTO {

    Long treatmentPhaseId;
    Long appointmentId;
    Long id;
    String note;

    public Long getTreatmentPhaseId() {
        return treatmentPhaseId;
    }

    public void setTreatmentPhaseId(Long treatmentPhaseId) {
        this.treatmentPhaseId = treatmentPhaseId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
