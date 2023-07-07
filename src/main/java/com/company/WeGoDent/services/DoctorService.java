package com.company.WeGoDent.services;


import com.company.WeGoDent.dto.DoctorDTO;
import com.company.WeGoDent.entity.*;
import com.company.WeGoDent.enums.DoctorType;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.forms.DoctorUserForm;
import com.company.WeGoDent.forms.UserForm;
import com.company.WeGoDent.records.GeoLocationInformation;
import com.company.WeGoDent.repositories.DoctorRepository;
import com.company.WeGoDent.services.helpers.GeocodingService;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GeocodingService geocodingService;


    public Doctor createDoctor(Doctor doctorForm){
        Doctor doctor = new Doctor();
        User user = accountService.createDoctorUser(doctorForm.getUser());

        if(user != null){
            doctor.setUser(user);
            getDoctorForm(doctorForm, doctor);

        } else {
            // Handle the scenario where User creation failed
            throw new RuntimeException("User creation failed. Doctor cannot be created.");
        }

        return doctor;

    }


    public List<Doctor> retrieveDoctorsByLocationAndType(DoctorType doctorType,
                                                         String officeLocation){

        GeoLocationInformation info = geocodingService.getLatLongObject(officeLocation);

        System.out.print(info.toString());
        System.out.println(doctorType);
        System.out.println(officeLocation);


        List<Doctor> doctorList = doctorRepository.findNearby(doctorType.ordinal(), info.getLongitude(), info.getLatitude(), 20);

        return doctorList;
    }





    public Doctor updateDoctor(Long doctorId, Doctor doctorForm){
        if(!doctorRepository.existsById(doctorId)){
            return null;
        }
        Doctor doctor = doctorRepository.findById(doctorId).get();

//        UserForm userForm = new UserForm();
//        userForm.email = doctorForm.email;
//        userForm.firstName = doctorForm.firstName;
//        userForm.lastName = doctorForm.lastName;
//        userForm.phoneNumber = doctorForm.phoneNumber;
//        userForm.password = doctorForm.password;
        accountService.updateUser(doctor.getUser().getId(),doctorForm.getUser());
        getDoctorForm(doctorForm, doctor);



        return doctor;
    }

    private void getDoctorForm(Doctor doctorForm, Doctor doctor) {
        doctor.setBiography(doctorForm.getBiography());
        doctor.setExperience(doctorForm.getExperience());
        doctor.setHourlyRate(doctorForm.getHourlyRate());
        doctor.setLanguage(doctorForm.getLanguage());
        doctor.setOfficeLocation(
                geocodingService.getPointObject(doctorForm.getOfficeLocationName())
        );
        doctor.setOfficeLocationName(doctorForm.getOfficeLocationName());
        doctor.setDoctorType(doctorForm.getDoctorType());
        doctorRepository.save(doctor);
    }

    public Boolean deleteDoctor(Long doctorId){

        if(doctorRepository.existsById(doctorId)){

            Doctor doctor = doctorRepository.findById(doctorId).get();
            accountService.deleteUser(doctor.getUser().getId()); // get relevant user and it's ID and delete user
            doctorRepository.deleteById(doctorId);

            return true;
        }

        return false;
    }


    public Doctor findById(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id" + doctorId));
        return doctor;
    }

    public List<Appointment> listAppointments(Long doctorId){
        if(doctorRepository.existsById(doctorId)){
            Doctor doctor = doctorRepository.findById(doctorId).get();
            return doctor.getAppointments();
        }
        return null;
    }


    public Double getDoctorRating(Long doctorId){
        return reviewService.getDoctorsRating(doctorId);
    }

    public List<Patient> getPatientList(Long doctorId) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id" + doctorId));
        return doctorRepository.findPatientsByDoctorId(doctorId);
    }

    public Doctor getDoctorByUserId(Long userId){
        return doctorRepository.findByUserId(userId);
    }



}
