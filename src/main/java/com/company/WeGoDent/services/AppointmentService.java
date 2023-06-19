package com.company.WeGoDent.services;


import com.company.WeGoDent.enums.AppointmentStatus;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.forms.AppointmentForm;
import com.company.WeGoDent.entity.*;
import com.company.WeGoDent.repositories.AppointmentRepository;
import com.company.WeGoDent.repositories.PatientPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private TreatmentPhaseService treatmentPhaseService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PatientPlanService patientPlanService;

    @Autowired
    private PlanTreatmentService planTreatmentService;

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


//


    // First time comers

    @Transactional
    public Appointment acceptAppointment(Long appointmentId, Long treatmentPhaseId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + appointmentId));

        appointment.setStatus(AppointmentStatus.SCHEDULED);
        TreatmentPhase treatmentPhase = treatmentPhaseService.findById(treatmentPhaseId);


        Plan plan = new Plan();
        plan.setDescription("Plan with treatment of : " + treatmentPhase.getName());
        plan.setName("Newly created plan");
        plan = planService.create(plan);

        PatientPlan patientPlan = new PatientPlan();
        patientPlan.setPlan(plan);
        patientPlan.setPlanStartDate(LocalDateTime.now());
        patientPlan.setPlanEndDate(LocalDateTime.now().plusMonths(1));
        patientPlan.setPatient(appointment.getPatient());

        patientPlanService.create(patientPlan);

        PlanTreatment planTreatment = new PlanTreatment();

        planTreatment.setPlan(plan);
        planTreatment.setTreatment(treatmentPhase.getTreatment());
        planTreatmentService.create(planTreatment);


        treatmentSessionService.createTreatmentSession(appointment,treatmentPhase);

        return appointment;
    }

    @Transactional
    public Appointment scheduleAppointment(Appointment appointment, Long treatmentPhaseId){

        Appointment createdApp = appointmentRepository.saveAndFlush(appointment);
        TreatmentPhase treatmentPhase = treatmentPhaseService.findById(treatmentPhaseId);
        treatmentSessionService.createTreatmentSession(createdApp,treatmentPhase);
        return createdApp;

    }


}
