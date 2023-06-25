package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.*;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.forms.PatientUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;




    @Autowired
    private AccountService accountService;

    public Patient createPatient(PatientUserForm patientUserForm){

        Patient patient = new Patient();

        User user = accountService.createPatientUser(patientUserForm);

        if(user != null){

            patient.setUser(user);

        } else {
            // Handle the scenario where User creation failed
            throw new RuntimeException("User creation failed. Doctor cannot be created.");
        }

        patientRepository.save(patient);

        return patient;

    }


    public Patient updatePatient(Long id,PatientUserForm patientUserForm){
        if(patientRepository.findById(id).isPresent()){
            Patient patient = patientRepository.findById(id).get();
            UserForm userForm = new UserForm();
            userForm.email = patientUserForm.email;
            userForm.firstName = patientUserForm.firstName;
            userForm.lastName = patientUserForm.lastName;
            userForm.phoneNumber = patientUserForm.phoneNumber;
            userForm.password = patientUserForm.password;
            accountService.updateUser(patient.getUser().getId(),userForm);
        }
        return null;
    }

    public Boolean deletePatient(Long id){
        if(patientRepository.existsById(id)){
            Patient patient = patientRepository.findById(id).get();
            accountService.deleteUser(patient.getUser().getId());
            patientRepository.delete(patient);
            return true;
        }
        return false;
    }

    public Patient findById(Long patientId){
        Patient selectedPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id" + patientId));

        return selectedPatient;
    }

    public List<Appointment> listAppointments(Long patientId){
        if(patientRepository.existsById(patientId)){
            Patient patient = patientRepository.findById(patientId).get();
            return patient.getAppointments();
        }
        return null;
    }

    public List<PatientPlan> listPlans(Long patientId){
        Patient selectedPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id" + patientId));

        return selectedPatient.getPatientPlanList();


    }

    public Patient findByUserId(Long userId){
        return patientRepository.findByUserId(userId);
    }


}
