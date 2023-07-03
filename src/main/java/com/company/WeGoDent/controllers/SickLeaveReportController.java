package com.company.WeGoDent.controller;

import com.company.WeGoDent.dto.SickLeaveReportDTO;
import com.company.WeGoDent.entity.SickLeaveReport;
import com.company.WeGoDent.mapper.SickLeaveReportMapper;
import com.company.WeGoDent.service.SickLeaveReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sick_leave_reports")
public class SickLeaveReportController {

    @Autowired

    private SickLeaveReportService sickLeaveReportService;
    @Autowired
    private SickLeaveReportMapper sickLeaveReportMapper;




    @GetMapping
    public ResponseEntity<List<SickLeaveReportDTO>> getAll() {
        List<SickLeaveReport> reports = sickLeaveReportService.getAll();
        return ResponseEntity.ok(reports.stream().map(sickLeaveReportMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{sickLeaveReportId}")
    public ResponseEntity<SickLeaveReportDTO> getOne(@PathVariable Long sickLeaveReportId) {
        SickLeaveReport report = sickLeaveReportService.getOne(sickLeaveReportId);
        return ResponseEntity.ok(sickLeaveReportMapper.toDto(report));
    }

    @PostMapping
    public ResponseEntity<SickLeaveReportDTO> save(@RequestBody SickLeaveReportDTO dto) {
        SickLeaveReport savedReport = sickLeaveReportService.save(sickLeaveReportMapper.toEntity(dto));
        return ResponseEntity.ok(sickLeaveReportMapper.toDto(savedReport));
    }

    @DeleteMapping("/{sickLeaveReportId}")
    public ResponseEntity<Void> delete(@PathVariable Long sickLeaveReportId) {
        sickLeaveReportService.delete(sickLeaveReportId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{sickLeaveReportId}")
    public ResponseEntity<SickLeaveReportDTO> update(@PathVariable Long sickLeaveReportId, @RequestBody SickLeaveReportDTO dto) {
        SickLeaveReport updatedReport = sickLeaveReportService.update(sickLeaveReportId, sickLeaveReportMapper.toEntity(dto));
        return ResponseEntity.ok(sickLeaveReportMapper.toDto(updatedReport));
    }



}
