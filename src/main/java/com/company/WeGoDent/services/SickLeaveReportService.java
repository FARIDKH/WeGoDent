package com.company.WeGoDent.service;

import com.company.WeGoDent.entity.SickLeaveReport;
import com.company.WeGoDent.repositories.SickLeaveReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SickLeaveReportService {

    private final SickLeaveReportRepository sickLeaveReportRepository;

    @Autowired
    public SickLeaveReportService(SickLeaveReportRepository sickLeaveReportRepository) {
        this.sickLeaveReportRepository = sickLeaveReportRepository;
    }

    public List<SickLeaveReport> getAll() {
        return sickLeaveReportRepository.findAll();
    }

    public SickLeaveReport getOne(Long id) {
        return sickLeaveReportRepository.findById(id).orElseThrow();
    }

    public SickLeaveReport save(SickLeaveReport sickLeaveReport) {
        return sickLeaveReportRepository.save(sickLeaveReport);
    }

    public void delete(Long id) {
        sickLeaveReportRepository.deleteById(id);
    }

    public SickLeaveReport update(Long id, SickLeaveReport newSickLeaveReport) {
        return sickLeaveReportRepository.findById(id)
                .map(sickLeaveReport -> {
                    sickLeaveReport.setPatient(newSickLeaveReport.getPatient());
                    sickLeaveReport.setDoctor(newSickLeaveReport.getDoctor());
                    sickLeaveReport.setReportContent(newSickLeaveReport.getReportContent());
                    sickLeaveReport.setReportDate(newSickLeaveReport.getReportDate());
                    return sickLeaveReportRepository.save(sickLeaveReport);
                })
                .orElseGet(() -> {
                    newSickLeaveReport.setId(id);
                    return sickLeaveReportRepository.save(newSickLeaveReport);
                });
    }

}
