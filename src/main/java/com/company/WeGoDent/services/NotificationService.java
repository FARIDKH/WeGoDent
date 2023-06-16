package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.*;
import com.company.WeGoDent.repositories.NotificationRepository;
import com.company.WeGoDent.services.helpers.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    public void createNotificationForAppointment(Appointment appointment){
        Notification notification = new Notification();
        notification.setAppointment(appointment);
        notification.setStatus(true);
        notification.setTimeSent(new Timestamp(System.currentTimeMillis()));
        notification.setMessage("Notifying for appointment");

        User patient = appointment.getPatient().getPatientId();
        Doctor doctor = appointment.getDoctor();

        try {
            emailService.sendAuthMessage(patient.getEmail(),
                    "WeGoDent.com | Patient Login Details", patient.getEmail(), patient.getPassword());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        emailService.sendAppointmentMessageToDoctor(doctor.getDoctorId().getEmail(),
                "WeGoDent.com | New Appointment request",
                doctor.getDoctorId().getFirstName() + " " + doctor.getDoctorId().getLastName(),
                patient.getFirstName() + " " + patient.getLastName(),
                appointment.getAppointmentStart(),
                doctor.getOfficeLocation()
                );

        notificationRepository.save(notification);

    }

    public void createNotificationForChangeInAppointment(Appointment appointment){
        Notification notification = new Notification();
        notification.setAppointment(appointment);
        notification.setStatus(true);
        notification.setTimeSent(new Timestamp(System.currentTimeMillis()));
        notification.setMessage("Notifying Patient for change in appointment");

        User patient = appointment.getPatient().getPatientId();
        Doctor doctor = appointment.getDoctor();

        try {
            emailService.sendAppointmentMessageToPatient(patient.getEmail(), "WeGoDent.com | CHANGE IN APPOINTMENT BY DOCTOR",
                    doctor.getDoctorId().getFirstName() + " " + doctor.getDoctorId().getLastName(),
                    patient.getFirstName() + " " + patient.getLastName(),
            appointment.getAppointmentStart(),
                    doctor.getOfficeLocation(),
            appointment.getStatus());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        notificationRepository.save(notification);

    }




}
