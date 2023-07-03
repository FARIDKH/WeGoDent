package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.SickLeaveReportType;
import com.company.WeGoDent.repositories.SickLeaveReportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SickLeaveReportTypeService {

    @Autowired
    private SickLeaveReportTypeRepository sickLeaveReportTypeRepository;



    public List<SickLeaveReportType> getAll() {
        return sickLeaveReportTypeRepository.findAll();
    }

    public SickLeaveReportType getOne(Long id) {
        return sickLeaveReportTypeRepository.findById(id).orElseThrow();
    }

    public SickLeaveReportType save(SickLeaveReportType sickLeaveReportType) {
        return sickLeaveReportTypeRepository.save(sickLeaveReportType);
    }

    public void delete(Long id) {
        sickLeaveReportTypeRepository.deleteById(id);
    }

    public SickLeaveReportType update(Long id, SickLeaveReportType newSickLeaveReportType) {
        return sickLeaveReportTypeRepository.findById(id)
                .map(reportType -> {
                    reportType.setCode(newSickLeaveReportType.getCode());
                    reportType.setDescription(newSickLeaveReportType.getDescription());
                    return sickLeaveReportTypeRepository.save(reportType);
                })
                .orElseGet(() -> {
                    newSickLeaveReportType.setId(id);
                    return sickLeaveReportTypeRepository.save(newSickLeaveReportType);
                });
    }
}
