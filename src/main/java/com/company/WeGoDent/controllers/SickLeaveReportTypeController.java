package com.company.WeGoDent.controllers;

import com.company.WeGoDent.entity.SickLeaveReportType;
import com.company.WeGoDent.services.SickLeaveReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sick_leave_reports/types")
public class SickLeaveReportTypeController {
    @Autowired
    private SickLeaveReportTypeService sickLeaveReportTypeService;

    @Autowired
    public SickLeaveReportTypeController(SickLeaveReportTypeService sickLeaveReportTypeService) {
        this.sickLeaveReportTypeService = sickLeaveReportTypeService;
    }

    @GetMapping
    public ResponseEntity<List<SickLeaveReportType>> getAll(@PathVariable Long reportId) {
        return ResponseEntity.ok(sickLeaveReportTypeService.getAll());
    }

    @GetMapping("/{typeId}")
    public ResponseEntity<SickLeaveReportType> getOne(@PathVariable Long reportId, @PathVariable Long typeId) {
        return ResponseEntity.ok(sickLeaveReportTypeService.getOne(typeId));
    }

    @PostMapping
    public ResponseEntity<SickLeaveReportType> save(@PathVariable Long reportId, @RequestBody SickLeaveReportType reportType) {
        return ResponseEntity.ok(sickLeaveReportTypeService.save(reportType));
    }

    @PutMapping("/{typeId}")
    public ResponseEntity<SickLeaveReportType> update( @PathVariable Long typeId, @RequestBody SickLeaveReportType reportType) {
        return ResponseEntity.ok(sickLeaveReportTypeService.update( typeId, reportType));
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<Void> delete(@PathVariable Long reportId, @PathVariable Long typeId) {
        sickLeaveReportTypeService.delete(typeId);
        return ResponseEntity.ok().build();
    }
}
