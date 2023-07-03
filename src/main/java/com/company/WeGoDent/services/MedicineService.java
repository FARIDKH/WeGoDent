package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Medicine;
import com.company.WeGoDent.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired

    private MedicineRepository medicineRepository;



    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public Medicine getOne(Long id) {
        return medicineRepository.findById(id).orElseThrow();
    }

    public Medicine save(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public void delete(Long id) {
        medicineRepository.deleteById(id);
    }

    public Medicine update(Long id, Medicine newMedicine) {
        return medicineRepository.findById(id)
                .map(medicine -> {
                    medicine.setName(newMedicine.getName());
                    medicine.setDosage(newMedicine.getDosage());
                    medicine.setInstructions(newMedicine.getInstructions());
                    return medicineRepository.save(medicine);
                })
                .orElseGet(() -> {
                    newMedicine.setId(id);
                    return medicineRepository.save(newMedicine);
                });
    }
}

