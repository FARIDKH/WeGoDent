package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.PrescriptionMedicine;
import com.company.WeGoDent.repositories.PrescriptionMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionMedicineService {

    @Autowired

    private PrescriptionMedicineRepository prescriptionMedicineRepository;



    public List<PrescriptionMedicine> getAll() {
        return prescriptionMedicineRepository.findAll();
    }

    public PrescriptionMedicine getOne(Long id) {
        return prescriptionMedicineRepository.findById(id).orElseThrow();
    }

    public PrescriptionMedicine save(PrescriptionMedicine prescriptionMedicine) {
        return prescriptionMedicineRepository.save(prescriptionMedicine);
    }

    public void delete(Long id) {
        prescriptionMedicineRepository.deleteById(id);
    }

    public PrescriptionMedicine update(Long id, PrescriptionMedicine newPrescriptionMedicine) {
        return prescriptionMedicineRepository.findById(id)
                .map(prescriptionMedicine -> {
                    prescriptionMedicine.setQuantity(newPrescriptionMedicine.getQuantity());
                    return prescriptionMedicineRepository.save(prescriptionMedicine);
                })
                .orElseGet(() -> {
                    newPrescriptionMedicine.setId(id);
                    return prescriptionMedicineRepository.save(newPrescriptionMedicine);
                });
    }
}

