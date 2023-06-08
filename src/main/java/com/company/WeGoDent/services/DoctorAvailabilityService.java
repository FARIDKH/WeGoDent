package com.company.WeGoDent.services;

import com.company.WeGoDent.forms.TimeSlotForm;
import com.company.WeGoDent.models.DoctorAvailability;
import com.company.WeGoDent.repositories.DoctorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAvailabilityService {

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    private DoctorService doctorService;

    public DoctorAvailability createAvailability(TimeSlotForm timeSlotForm){
        DoctorAvailability doctorAvailability = new DoctorAvailability();
        doctorAvailability.setStartDateTime(timeSlotForm.timeSlotStart);
        doctorAvailability.setEndDateTime(timeSlotForm.timeSlotEnd);
        doctorAvailability.setDoctor(
                doctorService.findById(timeSlotForm.doctorId)
        );

        doctorAvailabilityRepository.save(doctorAvailability);

        return doctorAvailability;
    }

    public void updateAvailability(TimeSlotForm timeSlotForm) {
        // Fetch all availabilities of the doctor that intersect with the new time slot
        List<DoctorAvailability> intersectingAvailabilities = doctorAvailabilityRepository.findByDoctorIdAndDateTimeRange(
                timeSlotForm.doctorId, timeSlotForm.timeSlotStart, timeSlotForm.timeSlotEnd);

        for (DoctorAvailability availability : intersectingAvailabilities) {

            if (availability.getStartDateTime().isEqual(timeSlotForm.timeSlotStart) &&
                    availability.getEndDateTime().isEqual(timeSlotForm.timeSlotEnd)) {
                return;
            }

            if (timeSlotForm.timeSlotStart.isAfter(availability.getStartDateTime())) {
                DoctorAvailability beforeNewAvailability = new DoctorAvailability();
                beforeNewAvailability.setDoctor(availability.getDoctor());
                beforeNewAvailability.setStartDateTime(availability.getStartDateTime());
                beforeNewAvailability.setEndDateTime(timeSlotForm.timeSlotStart);
                doctorAvailabilityRepository.save(beforeNewAvailability);
            }

            if (timeSlotForm.timeSlotEnd.isBefore(availability.getEndDateTime())) {
                DoctorAvailability afterNewAvailability = new DoctorAvailability();
                afterNewAvailability.setDoctor(availability.getDoctor());
                afterNewAvailability.setStartDateTime(timeSlotForm.timeSlotEnd);
                afterNewAvailability.setEndDateTime(availability.getEndDateTime());
                doctorAvailabilityRepository.save(afterNewAvailability);
            }

            // Delete the intersecting availability
            doctorAvailabilityRepository.delete(availability);
        }

        // Create and save the new availability
        DoctorAvailability newAvailability = new DoctorAvailability();
        newAvailability.setDoctor(doctorService.findById(timeSlotForm.doctorId));
        newAvailability.setStartDateTime(timeSlotForm.timeSlotStart);
        newAvailability.setEndDateTime(timeSlotForm.timeSlotEnd);
        doctorAvailabilityRepository.save(newAvailability);
    }
    public void deleteAvailability(TimeSlotForm timeSlotForm) {
        // Fetch all availabilities of the doctor that intersect with the new time slot
        List<DoctorAvailability> intersectingAvailabilities = doctorAvailabilityRepository.findByDoctorIdAndDateTimeRange(
                timeSlotForm.doctorId, timeSlotForm.timeSlotStart, timeSlotForm.timeSlotEnd);

        for (DoctorAvailability availability : intersectingAvailabilities) {
            if (timeSlotForm.timeSlotStart.isAfter(availability.getStartDateTime())) {
                DoctorAvailability beforeNewAvailability = new DoctorAvailability();
                beforeNewAvailability.setDoctor(availability.getDoctor());
                beforeNewAvailability.setStartDateTime(availability.getStartDateTime());
                beforeNewAvailability.setEndDateTime(timeSlotForm.timeSlotStart);
                doctorAvailabilityRepository.save(beforeNewAvailability);
            }

            if (timeSlotForm.timeSlotEnd.isBefore(availability.getEndDateTime())) {
                DoctorAvailability afterNewAvailability = new DoctorAvailability();
                afterNewAvailability.setDoctor(availability.getDoctor());
                afterNewAvailability.setStartDateTime(timeSlotForm.timeSlotEnd);
                afterNewAvailability.setEndDateTime(availability.getEndDateTime());
                doctorAvailabilityRepository.save(afterNewAvailability);
            }

            // Delete the intersecting availability
            doctorAvailabilityRepository.delete(availability);
        }
    }



    public List<DoctorAvailability> getAvailabilities(Long doctorId){
        return doctorAvailabilityRepository.findAllByDoctorId(doctorId);
    }

    public boolean deleteAvailability(Long id){
        if(doctorAvailabilityRepository.existsById(id)){
            doctorAvailabilityRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
