package com.company.WeGoDent.services;


import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.models.Doctor;
import com.company.WeGoDent.models.User;
import com.company.WeGoDent.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {


    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;


    public Doctor createDoctor(DoctorUserForm doctorForm){
        Doctor doctor = new Doctor();
        User user = userService.createDoctorUser(doctorForm);

        if(user != null){
            doctor.setDoctorId(user);
            doctor.setBiography(doctorForm.biography);
            doctor.setExperience(doctorForm.experience);
            doctor.setHourlyRate(doctorForm.hourly_rate);
            doctor.setLanguage(doctorForm.language);
            doctor.setOfficeLocation(doctorForm.office_location);
            doctorRepository.save(doctor);

        } else {
            // Handle the scenario where User creation failed
            throw new RuntimeException("User creation failed. Doctor cannot be created.");
        }

        return doctor;

    }

    public Doctor updateDoctor (Long doctorId, DoctorUserForm doctorForm){
        if(!doctorRepository.existsById(doctorId)){
            return null;
        }
        Doctor doctor = doctorRepository.findById(doctorId).get();
        User user = doctor.getDoctorId();
        doctor.setBiography(doctorForm.biography);
        doctor.setExperience(doctorForm.experience);
        doctor.setHourlyRate(doctorForm.hourly_rate);
        doctor.setLanguage(doctorForm.language);
        doctor.setOfficeLocation(doctorForm.office_location);
        doctorRepository.save(doctor);

        return doctor;
    }

    public Boolean deleteDoctor(Long doctorId){

        if(doctorRepository.existsById(doctorId)){
            doctorRepository.deleteById(doctorId);
        }

        return false;
    }




}
