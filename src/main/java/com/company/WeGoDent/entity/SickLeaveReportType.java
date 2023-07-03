package com.company.WeGoDent.entity;


import com.company.WeGoDent.enums.SickLeaveReportTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity(name = "sick_leave_report_types")
@Table
public class SickLeaveReportType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private SickLeaveReportTypeEnum code;

    private String description;


}
