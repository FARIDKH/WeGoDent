package com.company.WeGoDent.services;


import com.company.WeGoDent.enums.AppointmentStatus;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.forms.AppointmentForm;
import com.company.WeGoDent.entity.*;
import com.company.WeGoDent.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TreatmentSessionService treatmentSessionService;

    public Appointment createAppointment(AppointmentForm appointmentForm){
        Appointment appointment = new Appointment();
        appointment.setAppointmentEnd(appointmentForm.appointmentEnd);
        appointment.setAppointmentStart(appointmentForm.appointmentStart);

        Doctor doctor = doctorService.findById(appointmentForm.doctor_id);
        appointment.setDoctor(doctor);

        Patient patient = patientService.findById(appointmentForm.patient_id);
        appointment.setPatient(patient);

        appointment.setStatus(AppointmentStatus.REQUESTED);

        appointment = appointmentRepository.save(appointment);

        // Notify both partners

        notificationService.createNotificationForAppointment(appointment);



        return appointment;

    }

    public Appointment updateAppointment(Long id, AppointmentForm appointmentForm){
        if(appointmentRepository.existsById(id)){
            Appointment appointment = appointmentRepository.findById(id).get();


            appointment.setAppointmentStart(appointmentForm.appointmentStart);
            appointment.setAppointmentEnd(appointmentForm.appointmentEnd);
            appointment.setStatus(appointmentForm.appointmentStatus);


            appointment = appointmentRepository.save(appointment);

            notificationService.createNotificationForChangeInAppointment(appointment);

            return appointment;

        }
        return null;
    }

    public Appointment getAppointmentById(Long id){
        if(appointmentRepository.existsById(id)){
            Appointment appointment = appointmentRepository.findById(id).get();
            return appointment;

        }
        return null;
    }

//    @Transactional
//    public Appointment completeAppointment(Long appointmentId) {
//        Appointment appointment = appointmentRepository.findById(appointmentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + appointmentId));
//
//        // Assume you have an enum for status and 'COMPLETED' is one of them.
//        appointment.setStatus((AppointmentStatus.COMPLETED);
//
//        // Create TreatmentSession once Appointment is completed.
//        TreatmentPhase treatmentPhase = // fetch or determine the relevant TreatmentPhas
//        treatmentSessionService.createTreatmentSession(appointment, treatmentPhase);
//
//        return appointmentRepository.save(appointment);
//    }
//

}
