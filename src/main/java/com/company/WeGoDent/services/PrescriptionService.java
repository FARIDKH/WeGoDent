package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Prescription;
import com.company.WeGoDent.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription getOne(Long id) {
        return prescriptionRepository.findById(id).orElseThrow();
    }

    public Prescription save(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void delete(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public Prescription update(Long id, Prescription newPrescription) {
        return prescriptionRepository.findById(id)
                .map(prescription -> {
                    prescription.setProtocolNumber(newPrescription.getProtocolNumber());
                    prescription.setPrescriptionDate(newPrescription.getPrescriptionDate());
                    return prescriptionRepository.save(prescription);
                })
                .orElseGet(() -> {
                    newPrescription.setId(id);
                    return prescriptionRepository.save(newPrescription);
                });
    }
}
